package com.mtuci.rbpo.util;

import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.service.UserDBService;
import com.mtuci.rbpo.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDBService userService;

    public User getUserFromAuth(String auth) {
        String username = jwtTokenProvider.getUsername(auth.split(" ")[1]);
        return EntityCheck.getEntityOrThrowOptional(
            userService.findByUsername(username),
            "User not found by username: " + username
        );
    }
}
