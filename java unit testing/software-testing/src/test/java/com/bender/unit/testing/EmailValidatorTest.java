package com.bender.unit.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    public static EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "hello@test.com, true",
            "test.ua@test.com, true",
            "test.uatest.com, false"
    })
    void validateCorrectEmail(String email, boolean expectedResult) {
        //1 - given
        //2 - when
        boolean result = emailValidator.test(email);
        //3 - then
        assertThat(result).isEqualTo(expectedResult);
    }
}