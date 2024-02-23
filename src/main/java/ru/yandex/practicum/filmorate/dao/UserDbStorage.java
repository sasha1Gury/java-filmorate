package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Component
@Qualifier("userDbStorage")
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        String sqlQuery = "INSERT INTO \"Users\" (\"email\", \"login\", \"name\", \"birthday\") VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getName());
            final LocalDate birthday = user.getBirthday();
            if (birthday == null) {
                stmt.setNull(4, Types.DATE);
            } else {
                stmt.setDate(4, Date.valueOf(birthday));
            }
            return stmt;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());

        return user;
    }

    @Override
    public User updateUser(User user) {
        String sqlQuery = "UPDATE \"Users\" SET \"email\" = ?, " +
                "\"login\" = ?, \"name\" = ?, \"birthday\" = ? " +
                "WHERE \"user_id\" = ?;";

        int rowsAffected = jdbcTemplate.update(sqlQuery,
                user.getEmail(), user.getLogin(), user.getName(), Date.valueOf(user.getBirthday()), user.getId());

        if (rowsAffected < 1) {
            throw new NotFoundException(user.getId() + " не найден");
        }

        return user;
    }

    @Override
    public void deleteUser(long id) {
        String sqlQuery = "DELETE FROM \"Users\" where \"user_id\" = ?";

        jdbcTemplate.update(sqlQuery, id);

    }

    @Override
    public User getUserById(long id) {
        String sqlQuery = "SELECT * FROM \"Users\" WHERE \"user_id\" = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToUser, id);
        } catch (RuntimeException e) {
            throw new NotFoundException("id - " + id + " не найден");
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sqlQuery = "Select * from \"Users\"";

        return jdbcTemplate.query(sqlQuery, this::mapRowToUser);
    }

    //@Override
    public List<User> getUserFriends(User user) {
        String sqlQuery = "Select * from \"Users\" u " +
                "LEFT JOIN friends f on u.user_id=f.user1_id" +
                "WHERE user_id = ?";

        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, user.getId());
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("email"),
                resultSet.getString("login"),
                resultSet.getString("name"),
                resultSet.getDate("birthday").toLocalDate()
        );
    }
}
