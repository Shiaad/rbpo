package com.mtuci.rbpo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mtuci.rbpo.model.User;

public interface UserDBService {
    void save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void updateEmail(UUID userId, String newEmail);
    void updatePassword(UUID userId, String newPassword);
    User registerUser(String login, String password, String email);
    void deleteById(UUID id);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByUsername(String login);
}
