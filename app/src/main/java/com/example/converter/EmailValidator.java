package com.example.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс валидатор email
 * @author Vadim
 */
public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;
    /** регулярное выражение для email */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.(com|ru|net))$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /** Метод проверки строки по регулярному выражению
     * @param hex проверяемая строка
     */
    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }

}