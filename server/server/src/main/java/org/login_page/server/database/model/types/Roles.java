package org.login_page.server.database.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    ADMIN("ADMIN"),
    DEFAULT("DEFAULT");

    private final String role;
}
