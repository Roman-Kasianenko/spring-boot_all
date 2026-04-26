package com.bender.unit.testing;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EmailValidator implements Predicate<String> {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    @Override
    public boolean test(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
