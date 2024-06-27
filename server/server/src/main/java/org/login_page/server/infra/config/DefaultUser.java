package org.login_page.server.infra.config;

import org.login_page.server.application.dto.RegisterRequestDTO;
import org.login_page.server.application.service.user.UserService;
import org.login_page.server.database.model.User;
import org.login_page.server.database.model.types.Roles;
import org.login_page.server.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DefaultUser {
    @Autowired
    private Environment environment;

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            UserService userService
    ) {
        String username = environment.getProperty("name.admin");
        String password = environment.getProperty("password.admin");

        return args -> {
            if (userRepository.findByUserName(username).isEmpty()) {
                User admin = new User();

                admin.setUserName(username);
                admin.setPassword(password);
                admin.setRole(Roles.ADMIN);

                RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO(admin.getUsername(), admin.getPassword(), admin.getRole());

                userService.userRegister(registerRequestDTO);
            }
        };
    }
}
