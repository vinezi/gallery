package com.gallery.art.server.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean validateEmail(String email) {
        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return patternMatches(email, emailPattern);
    }

    public static boolean patternMatches(String value, String pattern) {
        return Pattern.compile(pattern)
                .matcher(value)
                .matches();
    }
}
