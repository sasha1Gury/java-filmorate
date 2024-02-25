package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.RatingMpa;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("FilmDbStorage")
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film addFilm(Film film) {
        film.initializeNullFields();
        if (isBefore1895(film)) {
            throw new ValidationException(String.format(
                    "Неверная дата фильма  %s",
                    film.getName()));
        }

        String sqlQuery = "INSERT INTO \"Film\" (\"name\", \"description\", \"release_date\", \"duration\"," +
                " \"rating_id\", \"rate\") VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            final LocalDate releaseDate = film.getReleaseDate();
            if (releaseDate == null) {
                stmt.setNull(3, Types.DATE);
            } else {
                stmt.setDate(3, Date.valueOf(releaseDate));
            }
            stmt.setInt(4, film.getDuration());
            stmt.setLong(5, film.getMpa().getId());
            stmt.setInt(6, film.getRate());
            return stmt;
        }, keyHolder);

        film.setId(keyHolder.getKey().longValue());

        if (film.getGenres() != null) {
            setGenres(film.getId(), film.extractGenreIds());
        }
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        String sqlQuery = "UPDATE \"Film\" SET \"name\" = ?, \"description\" = ?, \"release_date\" = ?, \"duration\" = ?," +
                " \"rating_id\" = ?, \"rate\" = ?" +
                "WHERE \"film_id\" = ?";

        int rowsNum = jdbcTemplate.update(sqlQuery,
                film.getName(), film.getDescription(), Date.valueOf(film.getReleaseDate()),
                film.getDuration(), film.getMpa().getId(), film.getRate(),
                film.getId());

        if (rowsNum < 1) {
            throw new NotFoundException(film.getId() + " не найден");
        }

        return film;
    }

    @Override
    public void deleteFilm(long id) {
        String sqlQuery = "DELETE FROM \"Film\" where \"film_id\" = ?";

        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public List<Film> getAllFilms() {
        String sqlQuery = "Select * from \"Film\"";

        return jdbcTemplate.query(sqlQuery, this::mapRowToFilm);
    }

    @Override
    public Film getFilmById(Long id) {
        String sqlQuery = "SELECT * FROM \"Film\" WHERE \"film_id\" = ?";

        String sqlQueryGenre = "SELECT g.\"name\" FROM \"Genre\" g \n" +
                "JOIN \"film_genre\" fg ON g.\"genre_id\" = fg.\"genre_id\" \n" +
                "JOIN \"Film\" f ON f.\"film_id\"=fg.\"film_id\" \n" +
                "WHERE f.\"film_id\" = 2;";


        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToFilm, id);
        } catch (RuntimeException e) {
            throw new NotFoundException("id - " + id + " не найден");
        }

    }

    public List<Film> getPopular(int count) {

        String sqlQuery = "SELECT * FROM \"Film\" f \n" +
                "LEFT JOIN \"likes\" l ON f.\"film_id\" = l.\"film_id\" \n" +
                "GROUP BY f.\"film_id\" \n" +
                "ORDER BY f.\"film_id\" DESC\n" +
                "LIMIT ?;";

        return jdbcTemplate.query(sqlQuery, this::mapRowToFilm, count);
    }

    private boolean isBefore1895(Film film) {
        LocalDate date = LocalDate.of(1895, 12, 28);
        return !film.getReleaseDate().isAfter(date);
    }

    private void setGenres(Long filmId, List<Long> genreIds) {
        String sqlQueryGenres = "INSERT INTO \"film_genre\" (\"film_id\", \"genre_id\") VALUES (?, ?)";
        for (Long genreId : genreIds) {
            jdbcTemplate.update(sqlQueryGenres, filmId, genreId);
        }
    }

    private Film.MPA getRatingMPA(Long id) {
        String sqlQueryRating = "SELECT * FROM \"rating\" r \n" +
                "JOIN \"Film\" f ON f.\"rating_id\" = r.\"rating_id\"\n" +
                "WHERE f.\"film_id\" = ?;";

        return jdbcTemplate.queryForObject(sqlQueryRating, this::mapRowToMPA, id);
    }

    private Film mapRowToFilm(ResultSet resultSet, int rowNum) throws SQLException {
        return new Film(
                resultSet.getLong("film_id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDate("release_date").toLocalDate(),
                resultSet.getInt("duration"),
                resultSet.getInt("rate"),
                getRatingMPA(resultSet.getLong("film_id")),
                new ArrayList<>()
        );
    }

    private Film.MPA mapRowToMPA(ResultSet resultSet, int rowNum) throws SQLException {
        Film.MPA obj = new Film.MPA();
        obj.setId(resultSet.getInt("rating_id"));
        obj.setName(RatingMpa.valueOfDisplayName(resultSet.getString("name")));
        return obj;
    }
}
