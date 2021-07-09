package com.spiet.bleiny.shared.infra;

import com.spiet.bleiny.shared.infra.utils.EmailValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
public class EmailValidatorTest {

    @Test
    @DisplayName("should be able to accept a valid email ")
    void testValidEmail() {
        var test = new EmailValidator("pedrosilva@gmail.com");
        Assertions.assertEquals(test.getEmail(), "pedrosilva@gmail.com");
    }

    @Test
    @DisplayName("should not be able to accept an invalid email")
    void testInvalidEmail() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new EmailValidator("jondoe"));
    }
}
