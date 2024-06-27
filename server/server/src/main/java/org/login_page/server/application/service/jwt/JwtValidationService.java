package org.login_page.server.application.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.login_page.server.infra.exception.ExJwtExpired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtValidationService {
    private final JwtGeneratorService jwtGeneratorService;

    /**
     * @param token       verifica se o token é valido testando o nome de usuário dentro do token e a validade
     * @param userDetails pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return boolean retorna se o usuário é valido ou não
     */
    public boolean isValidToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //INFO: Extração de valores do token

    /**
     * @param token           Extrai todos os dados do token
     * @param claimsTFunction função com claims e outro valor genérico
     * @return <T> Retorna um valor genérico
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsTFunction
    ) {
        final Claims claims;

        try {
            claims = extractAllClaims(token);
        } catch (ExpiredJwtException exception) {
            throw new ExJwtExpired("O token fornecido está expirado!");
        }

        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtGeneratorService.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @param token Extrai o nome de usuário do token e retorna em formato de string
     * @return String
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
