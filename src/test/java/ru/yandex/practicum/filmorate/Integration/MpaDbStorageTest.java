package ru.yandex.practicum.filmorate.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.dao.MpaDbStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class MpaDbStorageTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetAllMPAs() {
        MpaDbStorage mpaDbStorage = new MpaDbStorage(jdbcTemplate);

        List<Film.MPA> mpas = mpaDbStorage.getAllMPAs();

        assertThat(mpas).isNotNull();
        assertThat(mpas).hasSizeGreaterThan(0);
    }

    @Test
    void testGetMPAById() {
        MpaDbStorage mpaDbStorage = new MpaDbStorage(jdbcTemplate);


        Film.MPA mpa = mpaDbStorage.getMPAById(1L);

        assertThat(mpa).isNotNull();
        assertThat(mpa.getId()).isEqualTo(1L);
    }
}

