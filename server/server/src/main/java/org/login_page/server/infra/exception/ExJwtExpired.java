package org.login_page.server.infra.exception;

import lombok.Getter;

@Getter
public class ExJwtExpired extends RuntimeException {
    public ExJwtExpired(String message) {
        super(message);
    }
}
