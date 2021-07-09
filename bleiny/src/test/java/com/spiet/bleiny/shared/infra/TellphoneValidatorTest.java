package com.spiet.bleiny.shared.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.spiet.bleiny.shared.infra.utils.TellphoneValidator;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
public class TellphoneValidatorTest {

    @Test
    @DisplayName("Should be able create an tellphone")
    void testCreateTellphone() {
        var test = new TellphoneValidator("13996403088");
        Assertions.assertEquals(test.getNumber(), "13996403088");
    }

    @Test
    @DisplayName("should not be able create an tellphone")
    void testeInvalidTellphone() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TellphoneValidator("1399640308"));
    }
}
