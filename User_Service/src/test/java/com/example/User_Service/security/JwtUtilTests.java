package com.example.User_Service.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTests {
    private JwtUtil jwtUtil;
    private SecretKey secretKey;
    private String base64Secret;

    @BeforeEach
    void setUp() throws Exception {
        // 1) Generate a real HS256 key and Base64-encode it:
        secretKey    = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        base64Secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // 2) Instantiate and inject private fields via reflection:
        jwtUtil = new JwtUtil();
        Field secretField = JwtUtil.class.getDeclaredField("base64Secret");
        secretField.setAccessible(true);
        secretField.set(jwtUtil, base64Secret);

        Field expField = JwtUtil.class.getDeclaredField("expirationMs");
        expField.setAccessible(true);
        expField.setLong(jwtUtil, 36000000);
    }

    @Test
    void generateAndExtractUsername_andClaims() {
        String username = "alice";
        Long   userId   = 42L;
        String role     = "Admin";

        String token = jwtUtil.generateToken(username, userId, role);
        assertNotNull(token);

        // parse with the same key:
        Jws<Claims> parsed = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

        Claims claims = parsed.getBody();
        assertEquals(username, claims.getSubject());

        // numeric claims may come back as Integer or Long, so read as Number:
        Number idNum = claims.get("id", Number.class);
        assertEquals(userId.longValue(), idNum.longValue());

        assertEquals(role, claims.get("role", String.class));

        Date issued = claims.getIssuedAt();
        Date expires = claims.getExpiration();
        assertTrue(expires.after(issued), "Expiration must be after issue time");
    }

    @Test
    void validateToken_successAndUsernameMismatch() {
        String username = "bob";
        String token = jwtUtil.generateToken(username, 7L, "Customer");

        UserDetails goodDetails = mock(UserDetails.class);
        when(goodDetails.getUsername()).thenReturn(username);

        assertTrue(jwtUtil.validateToken(token, goodDetails));

        // mismatch user:
        UserDetails badDetails = mock(UserDetails.class);
        when(badDetails.getUsername()).thenReturn("someoneElse");
        assertFalse(jwtUtil.validateToken(token, badDetails));
    }

    @Test
    void validateToken_expiredToken() throws Exception {
        // shorten expiration
        Field expField = JwtUtil.class.getDeclaredField("expirationMs");
        expField.setAccessible(true);
        expField.setLong(jwtUtil, 1L);

        String token = jwtUtil.generateToken("x", 1L, "R");

        UserDetails details = mock(UserDetails.class);
        // no need to stub getUsername() because validateToken will return false
        // before ever calling it when the token is expired

        assertFalse(jwtUtil.validateToken(token, details),
                "Token with very short expiration should be considered expired");
    }

    @Test
    void getExpirationMs_returnsConfiguredValue() {
        assertEquals(36000000, jwtUtil.getExpirationMs());
    }
}
