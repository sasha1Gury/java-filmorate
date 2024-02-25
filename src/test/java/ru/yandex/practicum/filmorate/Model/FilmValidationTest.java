package ru.yandex.practicum.filmorate.Model;

/*import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;*/


public class FilmValidationTest {
/*    private final LocalValidatorFactoryBean  validator = new LocalValidatorFactoryBean();

    @BeforeEach
    public void setUp() {
        validator.afterPropertiesSet();
    }

    @Test
    public void validationTestValid() {
        Film film = new Film(1, "Name", "Description", LocalDate.now(), 5, new HashSet<>());

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void validationTestBlankName() {
        Film film = new Film(1, "", "Description", LocalDate.now(), 5, new HashSet<>());

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void validationTestInvalidDescriptionSize() {
        Film film = new Film(1, "Name", "", LocalDate.now(), 5, new HashSet<>());

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("description", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void validationTestNegativeDuration() {
        Film film = new Film(1, "Name", "Description", LocalDate.now(), -5, new HashSet<>());

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("duration", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void validationTestReleaseDate() {
        Film film = new Film(1, "Name", "Description", LocalDate.of(1895, 12, 28),
                5, new HashSet<>());
        FilmController filmController = new FilmController(new FilmService(new InMemoryFilmStorage()));
        assertThrows(ValidationException.class, () -> {
            filmController.addFilm(film);
        });
    }*/
}

