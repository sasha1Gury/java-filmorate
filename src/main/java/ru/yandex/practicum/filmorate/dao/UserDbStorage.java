package ru.yandex.practicum.filmorate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Component
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User user) {
        String sqlQuery = "INSERT INTO Users(user_id, email, login, name, birthday) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());

        return user;
    }

    @Override
    public User updateUser(User user) {
        String sqlQuery = "UPDATE Users set user_id = ?, email = ?, login = ?, name = ?, birthday = ? WHERE user_id = ?";

        jdbcTemplate.update(sqlQuery,
                user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());

        return user;
    }

    @Override
    public void deleteUser(long id) {
        String sqlQuery = "DELETE FROM Users where user_id = ?";

        jdbcTemplate.update(sqlQuery, id);

    }

    @Override
    public User getUserById(long id) {
        String sqlQuery = "SELECT * FROM Users WHERE user_id = ?";

        return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToUser, id);
    }

    @Override
    public List<User> getAllUsers() {
        String sqlQuery = "Select * from Users";

        return jdbcTemplate.query(sqlQuery, this::mapRowToUser);
    }

    @Override
    public List<User> getUserFriends(User user) {
        String sqlQuery = "Select * from Users u " +
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
                resultSet.getDate("birthday").toLocalDate(),
                new HashSet<>()
        );
    }
}
