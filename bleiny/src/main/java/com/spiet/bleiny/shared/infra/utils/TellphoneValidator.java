package com.spiet.bleiny.shared.infra.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class TellphoneValidator {
    private String number;

    public TellphoneValidator(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Number is necessary!");
        }
        if (!number.matches("^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$")) {
            throw new IllegalArgumentException("Invalid number");
        }
        this.number = number;
    }
}
