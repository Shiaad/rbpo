package com.mtuci.rbpo.controller;


import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtuci.rbpo.config.JwtTokenProvider;
import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.requests.LoginRequest;
import com.mtuci.rbpo.requests.LoginResponse;
import com.mtuci.rbpo.requests.RegistrationRequest;
import com.mtuci.rbpo.service.UserDBService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;
    private final UserDBService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(new LoginResponse(request.getUsername(), jwtProvider.createToken(request.getUsername(),
                    authenticationManager
                            .authenticate(
                                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))
                            .getAuthorities().stream().collect(Collectors.toSet()))));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(request);
        }
    }

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            User user = userService.registerUser(request.getUsername(), request.getPassword(), request.getEmail());
            return ResponseEntity.ok(user);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }
}