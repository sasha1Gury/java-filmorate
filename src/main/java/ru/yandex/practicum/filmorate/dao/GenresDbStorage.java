package ru.yandex.practicum.filmorate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.GenreEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class GenresDbStorage {

    private final JdbcTemplate jdbcTemplate;

    public GenresDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Film.Genre> getAllGenres() {
        String sqlQuery = "SELECT * FROM \"Genre\" g ";

        return jdbcTemplate.query(sqlQuery, this::mapRowToGenres);
    }

    public Film.Genre getGenreById(long id) {
        String sqlQuery = "SELECT * FROM \"Genre\" g WHERE g.\"genre_id\" = ?; ";

        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToGenres, id);
        } catch (RuntimeException e) {
            throw new NotFoundException(id + " не найден");
        }

    }

    private Film.Genre mapRowToGenres(ResultSet resultSet, int rowNum) throws SQLException {
        Film.Genre obj = new Film.Genre();
        obj.setId(resultSet.getInt("genre_id"));
        obj.setName(GenreEnum.valueOfDisplayName(resultSet.getString("name")).toString());
        return obj;
    }
}
