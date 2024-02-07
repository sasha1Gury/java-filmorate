package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NewUserException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    Map<Long, User> users = new HashMap<>();
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
            throw new NewUserException(String.format("Пользователь + " + user.getLogin() + " уже существует"));
        }
        user.setId(idCounter++);
        user.setFriends(new HashSet<>());
        users.put(user.getId(), user);
        log.info("Создан пользователь " + user.getLogin());
        return user;
    }

    public User updateUser(User user) throws NewUserException {
         if (users.containsKey(user.getId())) {
             user.setFriends(new HashSet<>());
             log.info("Пользователь " + users.get(user.getId()).getLogin() +  " перезаписан на " + user.getLogin());
             users.put(user.getId(), user);
         } else throw new NotFoundException(String.format("Пользователь с id " + user.getId() + " не найден "));

        return user;
    }

    public void deleteUser(long id) {
        if (users.containsKey(id)) {
            users.remove(id);
        } else {
            log.warn("пользователя с id = " + id + " не существует");
            throw new NotFoundException(String.format("пользователя с id = " + id + " не существует"));
        }
    }

    public User getUserById(long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            log.warn("пользователя с id = " + id + " не существует");
            throw new NotFoundException(String.format("пользователя с id = " + id + " не существует"));
        }
    }

    public List<User> getUserFriends(User user) {
        List<User> friends = new ArrayList<>();
        List<Long> friendsId = new ArrayList<>(user.getFriends());
        for (long i : friendsId) {
            User user1 = getUserById(i);
            friends.add(user1);
        }
        return friends;
    }
}
