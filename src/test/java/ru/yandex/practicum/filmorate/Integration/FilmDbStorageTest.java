package ru.yandex.practicum.filmorate.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.dao.FilmDbStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class FilmDbStorageTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testAddFilm() {
        // Prepare test data
        Film film = new Film();
        film.setName("Example Film");
        film.setDescription("An example film description");
        film.setReleaseDate(LocalDate.of(2023, 1, 1));
        film.setDuration(120);
        film.setMpa(new Film.MPA(1));

        FilmDbStorage filmDbStorage = new FilmDbStorage(jdbcTemplate);

        Film addedFilm = filmDbStorage.addFilm(film);

        assertThat(addedFilm).isNotNull();
        assertThat(addedFilm.getId()).isNotNull();
    }

    @Test
    void testGetAllFilms() {
        FilmDbStorage filmDbStorage = new FilmDbStorage(jdbcTemplate);

        Film film = new Film();
        film.setName("Example Film");
        film.setDescription("An example film description");
        film.setReleaseDate(LocalDate.of(2023, 1, 1));
        film.setDuration(120);
        film.setMpa(new Film.MPA(1));
        filmDbStorage.addFilm(film);

        List<Film> films = filmDbStorage.getAllFilms();

        assertThat(films).isNotNull();
        assertThat(films).hasSizeGreaterThan(0);
    }

}
