package org.login_page.server.application.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtGeneratorService {
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Método responsável por gerar o token baseado no userDetails que foi definido na entidade user
     *
     * @param userDetails referente aos detalhes do usuário
     * @return retorna o método que definitivamente cria o token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Método que criar o token e setar no token suas credenciais e data de expiração
     *
     * @param extractClaims local onde os dados do usuário será inserido
     * @param userDetails   referente aos detalhes do usuário
     * @return retorna o token do usuário
     */
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.YEAR, 1);
        Date nextYear = calendar.getTime();

        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(today)
                .setExpiration(nextYear)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }

    public Key getSecretKey() {
        return secretKey;
    }
}
