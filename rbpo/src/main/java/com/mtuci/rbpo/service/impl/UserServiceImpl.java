package com.mtuci.rbpo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mtuci.rbpo.model.UserDB;
import com.mtuci.rbpo.repository.UserRepository;
import com.mtuci.rbpo.repository.UserDetailsRepository;
import com.mtuci.rbpo.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository detailsRepository;

    @Override
    public void save(UserDB user) {
        user.getDetails().forEach(details -> {
            details.setUser(user);
            detailsRepository.save(details);
        });
        userRepository.save(user);
    }

    @Override
    public List<UserDB> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDB findById(long id) {
        return userRepository.findById(id).orElse(new UserDB());
    }
}
