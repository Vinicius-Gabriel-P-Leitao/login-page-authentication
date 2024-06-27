package org.login_page.server.application.dto;

import lombok.Builder;
import org.login_page.server.database.model.types.Roles;

@Builder
public record RegisterRequestDTO(String userName, String password, Roles roles) {
}
