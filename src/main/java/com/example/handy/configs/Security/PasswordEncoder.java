package com.example.handy.configs.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
public class PasswordEncoder {

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
