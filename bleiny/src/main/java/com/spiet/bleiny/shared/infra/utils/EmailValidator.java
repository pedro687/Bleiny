package com.spiet.bleiny.shared.infra.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailValidator {
    private String email;

    public EmailValidator(String email) {
        if (email == null ||
                !email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
            throw new IllegalArgumentException("Invalid Email");
        }
        this.email = email;
    }
}
