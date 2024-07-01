package org.login_page.server.application.service.jwt;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class JwtGeneratorServiceTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtGeneratorService jwtGeneratorService;

    /**
     * Utiliza o Mockito para inicializar os mocks
     */
    public JwtGeneratorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Testa se o token gerado é válido sem claims
     */
    @Test
    void generateTokenWithoutClaims() {
        // Mock userDetails
        when(userDetails.getUsername()).thenReturn("testUser");

        // Generate token
        String token = jwtGeneratorService.generateToken(userDetails);

        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    /**
     * Testa se o token gerado é válido criando algumas claims
     */
    @Test
    void generateTokenWithClaims() {
        // Mock userDetails
        when(userDetails.getUsername()).thenReturn("testUser");

        // Prepare claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "admin");

        // Generate token
        String token = jwtGeneratorService.generateToken(claims, userDetails);

        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    /**
     * Testa se o token gerado é válido
     */
    @Test
    void generatedTokenIsValid() {
        // Mock userDetails
        when(userDetails.getUsername()).thenReturn("testUser");

        // Generate token
        String token = jwtGeneratorService.generateToken(userDetails);

        // Validate token
        JwtValidationService validationService = new JwtValidationService(jwtGeneratorService);
        boolean isValid = validationService.isValidToken(token, userDetails);

        // Assert
        assertTrue(isValid);
    }
}
