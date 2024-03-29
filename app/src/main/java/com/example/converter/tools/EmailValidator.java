package com.example.converter.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email validator class
 * @author Vadim
 */
public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;
    /** regular expression for email */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.(com|ru|net))$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /** Method for checking a string against a regular expression
     * @param hex checked string
     */
    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }

}