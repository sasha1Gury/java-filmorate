package ru.yandex.practicum.filmorate.Model;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserValidationTest {
    private final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

    @BeforeEach
    public void setUp() {
        validator.afterPropertiesSet();
    }

    @Test
    public void validationTestValidUser() {
        User validUser = new User(1, "test@example.com", "Login", "John Doe", LocalDate.now(), new HashSet<>());

        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validationTestInvalidEmail() {
        User invalidUser = new User(1, "invalidEmail", "Login", "John Doe", LocalDate.now(), new HashSet<>());

        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("email", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void validationTestBlankLogin() {
        User invalidUser = new User(1, "test@example.com", "", "John Doe", LocalDate.now(), new HashSet<>());

        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("login", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void validationTestInvalidBirthday() {
        User invalidUser = new User(1, "test@example.com", "Login", "John Doe",
                LocalDate.of(2050, 1, 1), new HashSet<>());

        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("birthday", violations.iterator().next().getPropertyPath().toString());
    }
}
