package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

@Service
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(InMemoryUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addFriend(Long id, Long friendId) {
        userStorage.getUserById(id).addFriend(friendId);
        userStorage.getUserById(friendId).addFriend(id);
    }

    public void deleteFriend(Long id, Long friendId) {
        userStorage.getUserById(id).deleteFriend(friendId);
        userStorage.getUserById(friendId).deleteFriend(id);
    }
}
