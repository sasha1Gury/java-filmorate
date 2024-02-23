package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.FriendsDbStorage;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserDbService {

    private final UserStorage userDbStorage;
    private final FriendsDbStorage friendsDbStorage;

    @Autowired
    public UserDbService(UserStorage userDbStorage, FriendsDbStorage friendsDbStorage) {
        this.userDbStorage = userDbStorage;
        this.friendsDbStorage = friendsDbStorage;
    }

    public User getUserById(long id) {
        return userDbStorage.getUserById(id);
    }

    public User addUser(User user) {
        return userDbStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userDbStorage.updateUser(user);
    }

    public void deleteUser(long id) {
        userDbStorage.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userDbStorage.getAllUsers();
    }

    public void addFriend(Long id, Long friendId) {
        friendsDbStorage.addFriend(id, friendId);
    }
}
