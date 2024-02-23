package ru.yandex.practicum.filmorate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class FriendsDbStorage {
    private final JdbcTemplate jdbcTemplate;

    public FriendsDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addFriend(Long id, Long friendId) {
        String sqlQuery = "INSERT INTO \"friends\" (\"user1_id\", \"user2_id\", \"status\") VALUES (?, ?, ?)";

        try {
            jdbcTemplate.update(sqlQuery, id, friendId, "NOT_CONFIRMED");
        } catch (RuntimeException e) {
            throw new NotFoundException("id не наден");
        }

    }

    public void deleteFriend(Long id, Long friendId) {
        String sqlQuery = "DELETE FROM \"friends\" where \"user1_id\" = ? and \"user2_id\" = ?";

        jdbcTemplate.update(sqlQuery, id, friendId);
    }

    public List<User> getAllFriend(Long id) {
        String sqlQuery = "Select * from \"friends\" f " +
                "LEFT JOIN \"Users\" u on f.\"user2_id\"=u.\"user_id\"" +
                "WHERE f.\"user1_id\" = ?";

        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, id);
    }

    public List<User> findCommonFriends(Long id, Long friendId) {
        String sqlQuery = "SELECT *\n" +
                "FROM\n" +
                "    (SELECT *\n" +
                "     FROM \"friends\" F1\n" +
                "     JOIN \"Users\" U ON U.\"user_id\" = F1.\"user2_id\"\n" +
                "     WHERE F1.\"user1_id\" = ?) AS T1\n" +
                "JOIN\n" +
                "    (SELECT *\n" +
                "     FROM \"friends\" F2\n" +
                "     JOIN \"Users\" U ON U.\"user_id\" = F2.\"user2_id\"\n" +
                "     WHERE F2.\"user1_id\" = ?) AS T2\n" +
                "ON T1.\"user2_id\" = T2.\"user2_id\";";

        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, id, friendId);
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
