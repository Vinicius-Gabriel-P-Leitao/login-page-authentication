package org.login_page.server.infra.exception;

import lombok.Getter;

@Getter
public class ExNotFound extends RuntimeException {
    public ExNotFound(String message) {
        super(message);
    }
}
