package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    /*private final UserStorage userStorage;

    @Autowired
    public UserService(@Qualifier("InMemoryUserStorage") UserStorage InMemoryUserStorage) {
        this.userStorage = InMemoryUserStorage;
    }

    public User getUserById(long id) {
        return userStorage.getUserById(id);
    }

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public void deleteUser(long id) {
        userStorage.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public void addFriend(Long id, Long friendId) {
        User user = userStorage.getUserById(id);
        User userFriend = userStorage.getUserById(friendId);
        user.addFriend(friendId);
        userFriend.addFriend(id);
    }

    public void deleteFriend(Long id, Long friendId) {
        userStorage.getUserById(id).deleteFriend(friendId);
        userStorage.getUserById(friendId).deleteFriend(id);
    }

    public List<User> getAllFriend(Long id) {
        User user = userStorage.getUserById(id);
        return userStorage.getUserFriends(user);
    }

    public List<User> findCommonFriends(Long id, Long friendId) {
        Set<User> commonFriends = new HashSet<>();

        User user1 = userStorage.getUserById(id);
        User user2 = userStorage.getUserById(friendId);

        if (user1 != null && user2 != null) {
            commonFriends.addAll(userStorage.getUserFriends(user1));
            commonFriends.retainAll(userStorage.getUserFriends(user2));
        }

        if (commonFriends.isEmpty()) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(commonFriends);
        }
    }*/
}
