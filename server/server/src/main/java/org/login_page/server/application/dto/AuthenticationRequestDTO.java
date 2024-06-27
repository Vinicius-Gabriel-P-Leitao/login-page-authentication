package org.login_page.server.application.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequestDTO(String userName, String password) {
}
