package org.login_page.server.application.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.login_page.server.infra.exception.ExJwtExpired;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtValidationServiceTest {
    @Mock
    private JwtGeneratorService jwtGeneratorService;

    @InjectMocks
    private JwtValidationService jwtValidationService;

    private Key secretKey;

    /**
     * Inicializa os mocks e a classe de serviço de gerador de token
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        when(jwtGeneratorService.getSecretKey()).thenReturn(secretKey);
    }

    /**
     * Testa se o token gerado é válido
     */
    @Test
    void validTokenIsValidated() {
        // Create userDetails
        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());

        // Generate token
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiry
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // Validate token
        boolean isValid = jwtValidationService.isValidToken(token, userDetails);

        // Assert
        assertTrue(isValid);
    }

    /**
     * Testa se o token gerado é inválido se for invalido ele verifica mensagem de erro
     */
    @Test
    void expiredTokenIsNotValidated() {
        // Create userDetails
        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());

        // Generate expired token
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)) // 24 hours ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60)) // 1 hour ago
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // Validate token and assert exception
        Exception exception = assertThrows(ExJwtExpired.class, () -> {
            jwtValidationService.isValidToken(token, userDetails);
        });

        // Assert exception message
        String expectedMessage = "O token fornecido está expirado!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Testa se o extractUsername está funcionando corretamente
     */
    @Test
    void extractUserNameFromToken() {
        // Create token
        String token = Jwts.builder()
                .setSubject("testUser")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiry
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // Extract username
        String username = jwtValidationService.extractUserName(token);

        // Assert
        assertEquals("testUser", username);
    }
}
