package ru.yandex.practicum.filmorate.Integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.dao.UserDbStorage;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserDbStorageTest {
    private final JdbcTemplate jdbcTemplate;

    @Test
    public void testAddUser() {
        // Подготавливаем данные для теста
        User newUser = new User(1L, "user@email.ru", "vanya123", "Ivan Petrov", LocalDate.of(1990, 1, 1));
        UserDbStorage userStorage = new UserDbStorage(jdbcTemplate);

        // вызываем тестируемый метод
        User addedUser = userStorage.addUser(newUser);

        // проверяем утверждения
        assertThat(addedUser)
                .isNotNull() // проверяем, что объект не равен null
                .extracting(User::getId) // извлекаем ID добавленного пользователя
                .isNotNull(); // проверяем, что ID не равен null
    }

    @Test
    public void testGetUserById() {

        User newUser = new User(1L, "user@email.ru", "vanya123", "Ivan Petrov", LocalDate.of(1990, 1, 1));
        UserDbStorage userStorage = new UserDbStorage(jdbcTemplate);
        User addedUser = userStorage.addUser(newUser);

        User savedUser = userStorage.getUserById(addedUser.getId());

        // проверяем утверждения
        assertThat(savedUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(newUser);
    }
}

