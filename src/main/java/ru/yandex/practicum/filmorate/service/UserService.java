package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Long> getAllFriend(Long id) {
        return new ArrayList<>(userStorage.getUserById(id).getFriends());
    }

    public List<Long> findCommonFriends(Long id, Long friendId) {
        Set<Long> commonFriends = new HashSet<>();

        User user1 = userStorage.getUserById(id);
        User user2 = userStorage.getUserById(friendId);

        if (user1 != null && user2 != null) {
            commonFriends.addAll(user1.getFriends());
            commonFriends.retainAll(user2.getFriends());
        }

        return new ArrayList<>(commonFriends);
    }
}
