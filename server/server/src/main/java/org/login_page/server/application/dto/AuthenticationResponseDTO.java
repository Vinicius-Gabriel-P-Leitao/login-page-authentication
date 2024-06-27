package org.login_page.server.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponseDTO(@JsonProperty("token") String token) {
}
