package org.login_page.server.application.service.user;

import lombok.RequiredArgsConstructor;
import org.login_page.server.application.dto.AuthenticationRequestDTO;
import org.login_page.server.application.dto.AuthenticationResponseDTO;
import org.login_page.server.application.dto.RegisterRequestDTO;
import org.login_page.server.application.service.jwt.JwtGeneratorService;
import org.login_page.server.application.service.jwt.JwtValidationService;
import org.login_page.server.database.model.User;
import org.login_page.server.database.repository.UserRepository;
import org.login_page.server.infra.exception.ExNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGeneratorService jwtGeneratorService;
    private final AuthenticationManager authenticationManager;

    @PreAuthorize("hasRole('ADMIN')")
    public AuthenticationResponseDTO userRegister(RegisterRequestDTO request) {
        User user = new User();
        user.setUserName(request.userName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.roles());

        userRepository.save(user);
        var jwtToken = jwtGeneratorService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );

        var user = this.userIsPresent(request.userName());
        var jwtToken = jwtGeneratorService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public User userIsPresent(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new ExNotFound("Usuário inserido não foi encontrado!"));
    }
}
