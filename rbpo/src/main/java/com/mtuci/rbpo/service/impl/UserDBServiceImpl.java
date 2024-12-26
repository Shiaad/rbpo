package com.mtuci.rbpo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.model.UserRole;
import com.mtuci.rbpo.repository.UserDBRepository;
import com.mtuci.rbpo.service.UserDBService;
import com.mtuci.rbpo.util.EntityCheck;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserDBServiceImpl implements UserDBService {

    private final UserDBRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateEmail(UUID userId, String newEmail) {
        User user = EntityCheck.getEntityOrThrowOptional(userRepository.findById(userId), "User not found for id: " + userId);
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(UUID userId, String newPassword) {
        User user = EntityCheck.getEntityOrThrowOptional(userRepository.findById(userId), "User not found for id: " + userId);
        user.setPassword(newPassword);
        userRepository.save(user);
        
    }

    @Override
    public User registerUser(String login, String password, String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("Email already taken: " + email);
        }

        if (userRepository.findByUsername(login).isPresent()) {
            throw new IllegalStateException("Login already taken: " + login);
        }


        User newUser = new User();
        newUser.setUsername(login);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setRole(UserRole.ADMIN);
        return userRepository.save(newUser);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean existsUserByUsername(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        return userRepository.existsByUsername(login);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return userRepository.existsByEmail(email);
    }

}