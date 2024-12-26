package com.mtuci.rbpo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.model.UserDetailsImpl;

import com.mtuci.rbpo.repository.UserDBRepository;

import com.mtuci.rbpo.util.EntityCheck;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDBRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = EntityCheck.getEntityOrThrowOptional(userRepository.findByUsername(username), "User not found for username: " + username);
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getRole().getGrantedAuthorities(), true);
    }
}