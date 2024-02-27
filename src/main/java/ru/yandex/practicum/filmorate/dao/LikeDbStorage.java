package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;

@Component
public class LikeDbStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LikeDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void likeFilm(Long filmId, Long userId) {
        String sqlQuery = "INSERT INTO \"likes\" (\"film_id\", \"user_id\") VALUES (?, ?);";

        jdbcTemplate.update(sqlQuery, filmId, userId);
    }

    public void unlikeFilm(Long filmId, Long userId) {
        String sqlQuery = "DELETE FROM \"likes\" WHERE \"film_id\" = ? AND \"user_id\" = ?;";

        int rows = jdbcTemplate.update(sqlQuery, filmId, userId);

        if (rows < 1) {
            throw  new NotFoundException(filmId + " или " + userId + "не существует");
        }
    }
}
