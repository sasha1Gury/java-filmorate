package ru.yandex.practicum.filmorate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.RatingMpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MpaDbStorage {

    private final JdbcTemplate jdbcTemplate;

    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Film.MPA> getAllMPAs() {
        String sqlQuery = "SELECT * FROM \"rating\" r ";

        return jdbcTemplate.query(sqlQuery, this::mapRowToMPA);
    }

    public Film.MPA getMPAById(long id) {
        String sqlQuery = "SELECT * FROM \"rating\" r WHERE r.\"rating_id\" = ?; ";

        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToMPA, id);
        } catch (RuntimeException e) {
            throw new NotFoundException(id + " не найден");
        }
    }

    private Film.MPA mapRowToMPA(ResultSet resultSet, int rowNum) throws SQLException {
        Film.MPA obj = new Film.MPA();
        obj.setId(resultSet.getInt("rating_id"));
        obj.setName(RatingMpa.valueOfDisplayName(resultSet.getString("name")).toString());
        return obj;
    }
}
