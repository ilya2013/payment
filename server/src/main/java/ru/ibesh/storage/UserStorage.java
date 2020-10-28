package ru.ibesh.storage;

import ru.ibesh.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User addUser(User user);
    Optional<User> getUser(Long id);
    Optional<User> findUserByLogin(String login);
}
