package ru.yandex.practicum.filmorate.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.dao.GenresDbStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class GenresDbStorageTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetAllGenres() {
        GenresDbStorage genresDbStorage = new GenresDbStorage(jdbcTemplate);

        // Call the method
        List<Film.Genre> genres = genresDbStorage.getAllGenres();

        // Assertions
        assertThat(genres).isNotNull();
        assertThat(genres).hasSizeGreaterThan(0);
    }

    @Test
    void testGetGenreById() {
        GenresDbStorage genresDbStorage = new GenresDbStorage(jdbcTemplate);

        Film.Genre genre = genresDbStorage.getGenreById(1L);

        assertThat(genre).isNotNull();
        assertThat(genre.getId()).isEqualTo(1L);
    }
}

