package com.DEMO.backend.member.domain;

import com.DEMO.backend.global.exception.BadRequestException;
import com.DEMO.backend.global.exception.ExceptionCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.DEMO.backend.global.exception.ExceptionCode.*;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class Password {

    private static final String PASSWORD_FORMAT = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_FORMAT);

    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password encrypt(String value, PasswordEncoder encoder) {
        validate(value);
        return new Password(encoder.encode(value));
    }

    public Password update(String value, PasswordEncoder encoder) {
        return Password.encrypt(value, encoder);
    }

    public boolean isSame(String password) {
        return value.equals(password);
    }

    private static void validate(String value) {
        if (isNotValid(value)) {
            throw new BadRequestException(PASSWORD_IS_NOT_WRONG_PATTERN);
        }

        if (Objects.isNull(value)) {
            throw new NullPointerException("Password is not null.");
        }
    }

    private static boolean isNotValid(String value) {
        return !PASSWORD_PATTERN.matcher(value).matches();
    }

}
