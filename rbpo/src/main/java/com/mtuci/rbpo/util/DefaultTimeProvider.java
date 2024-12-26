package com.mtuci.rbpo.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultTimeProvider implements TimeProvider {
    @Override
    public Date now() {
        return new Date();
    }
}
