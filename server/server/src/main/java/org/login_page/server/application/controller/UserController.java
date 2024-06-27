package org.login_page.server.application.controller;

import lombok.RequiredArgsConstructor;
import org.login_page.server.application.dto.AuthenticationRequestDTO;
import org.login_page.server.application.dto.AuthenticationResponseDTO;
import org.login_page.server.application.dto.RegisterRequestDTO;
import org.login_page.server.application.service.user.UserService;
import org.login_page.server.database.model.types.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/v1/register")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponseDTO> registerUser(
            @RequestHeader(name = "username") String userName,
            @RequestHeader(name = "password") String password,
            @RequestHeader(name = "role") Roles role
    ) {
        RegisterRequestDTO request = new RegisterRequestDTO(userName, password, role);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.userRegister(request));
    }

    @PostMapping("/v1/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(@RequestHeader(name = "username") String userName,
                                                                      @RequestHeader(name = "password") String password) {
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO(userName, password);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.authenticate(authenticationRequestDTO));
    }
}
