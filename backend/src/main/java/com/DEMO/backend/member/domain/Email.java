package com.DEMO.backend.member.domain;

import com.DEMO.backend.global.exception.BadRequestException;
import com.DEMO.backend.global.exception.ExceptionCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.DEMO.backend.global.exception.ExceptionCode.*;

@Embeddable
public class Email {

    private static final String EMAIL_REGEX = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$";

    @Column(name = "email", unique = true)
    private String value;

    public Email(final String value) {
        this.value = value;
    }

    public static Email from(String value) {
        validate(value);
        return new Email(value);
    }

    private static void validate(final String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("Email is not null.");
        }

        if (validMatchEmailForm(value)) {
            throw new BadRequestException(EMAIL_IS_NOT_MATCH_FORM);
        }
    }

    private static boolean validMatchEmailForm(final String value) {
        return !Pattern.matches(EMAIL_REGEX, value);
    }


}
