package com.bender.unit.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {

    private final PasswordValidator underTest = new PasswordValidator();

    @Test
    void willFailIfPasswordNull() {
        //1 - given
        String password = null;
        //2 - when
        var actual = underTest.test(password);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordIsEmpty() {
        //1 - given
        String password = "";
        //2 - when
        var actual = underTest.test(password);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordIsEmptyHasWhitespaces() {
        //1 - given
        String password = "   ";
        //2 - when
        var actual = underTest.test(password);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordDoesNotMatchMinimumLength() {
        //1 - given
        String password = "      pass";
        //2 - when
        var actual = underTest.test(password);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordDoesNotContainsAtLeastOneDigit() {
        //1 - given
        String password = "password";
        //2 - when
        var actual = underTest.test(password);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordDoesNotContainsAtLeastOneSpecialSymbol() {
        //1 - given
        String password = "password1";
        //2 - when
        var actual = underTest.test(password);
        //3 - then
        assertThat(actual).isFalse();
    }
}
