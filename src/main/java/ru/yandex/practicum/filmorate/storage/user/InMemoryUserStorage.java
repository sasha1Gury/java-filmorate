package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NewUserException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    Map<Integer, User> users = new HashMap<>();
    private int idCounter = 1;

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User addUser(User user) throws NewUserException {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (users.containsValue(user)) {
            log.warn("Пользователь + " + user.getLogin() + " уже существует");
            throw new NewUserException(user.getLogin());
        }
        user.setId(idCounter++);
        users.put(user.getId(), user);
        log.info("Создан пользователь " + user.getLogin());
        return user;
    }

    public User updateUser(User user) throws NewUserException {
        if (users.containsValue(user)) {
            throw new NewUserException(user.getName());
        }
        log.info("Пользователь " + users.get(user.getId()).getLogin() +  " перезаписан на " + user.getLogin());
        users.put(user.getId(), user);
        return user;
    }

    public void deleteUser(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
        } else log.warn("пользователя с id = " + id + " не существует");
    }
}
