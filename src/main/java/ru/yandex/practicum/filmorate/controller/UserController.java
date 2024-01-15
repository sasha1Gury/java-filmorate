package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NewUserException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    Map<Integer, User> users = new HashMap<>();
    private int idCounter = 1;

    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) throws NewUserException {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (users.containsValue(user)) {
            log.warn("пользователь + " + user.getLogin() + " не создан");
            throw new NewUserException(user.getLogin());
        }
        user.setId(idCounter++);
        users.put(user.getId(), user);
        log.info("Создан пользователь " + user.getLogin());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws NewUserException {
        if (users.containsValue(user)) {
            throw new NewUserException(user.getName());
        }
        log.info("Пользователь " + users.get(user.getId()).getLogin() +  " перезаписан на " + user.getLogin());
        users.put(user.getId(), user);
        return user;
    }
}
