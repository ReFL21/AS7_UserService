package com.example.User_Service.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.User_Service.security.JwtDecoderConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtDecoderConfig.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class JwtDecoderConfigDiffblueTest {
    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Autowired
    private JwtDecoderConfig jwtDecoderConfig;


    @Test
    @DisplayName("Test jwtDecoder(); then return decode 'ABC123' is 'null'")
    @Tag("MaintainedByDiffblue")
    void testJwtDecoder_thenReturnDecodeAbc123IsNull() throws JwtException {
        // Arrange, Act and Assert
        assertTrue(jwtDecoderConfig instanceof com.example.User_Service.security.JwtDecoderConfig);
        assertNull(jwtDecoderConfig.jwtDecoder().decode("ABC123"));
    }
}
