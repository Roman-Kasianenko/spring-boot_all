package com.bender.unit.testing;

import java.util.function.Predicate;

public class PasswordValidator implements Predicate<String> {

    private static final String SPECIAL_CHAR_REGEX = ".*[!@#$%^&*()_+=<>?/\\[\\]{}|].*";

    @Override
    public boolean test(String password) {
        if(password == null || password.isBlank() || password.trim().length() < 8) {
            return false;
        }

        if(!password.matches(".*\\d.*")) {
            return false;
        }

        if(!password.matches(SPECIAL_CHAR_REGEX)) {
            return false;
        }

        return true;
    }
}
