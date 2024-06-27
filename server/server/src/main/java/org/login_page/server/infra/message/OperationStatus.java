package org.login_page.server.infra.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationStatus {
    @JsonProperty("status")
    private int httpStatus;

    @JsonProperty("message")
    private String httpMessage;
}