package com.example.User_Service.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PasswordEncoderConfig.class})
@ExtendWith(SpringExtension.class)
class PasswordEncoderConfigDiffblueTest {
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;


    @Test
    @DisplayName("Test passwordEncoder(); given PasswordEncoderConfig")
    @Tag("MaintainedByDiffblue")
    void testPasswordEncoder_givenPasswordEncoderConfig() {
        // Arrange, Act and Assert
        assertTrue(passwordEncoderConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }


    @Test
    @DisplayName("Test passwordEncoder(); given PasswordEncoderConfig (default constructor)")
    @Tag("MaintainedByDiffblue")
    void testPasswordEncoder_givenPasswordEncoderConfig2() {
        // Arrange, Act and Assert
        assertTrue((new PasswordEncoderConfig()).passwordEncoder() instanceof BCryptPasswordEncoder);
    }
}
