package ru.ibesh.storage;

import ru.ibesh.User;

import java.util.HashMap;
import java.util.Optional;

public class UserStorageImpl implements UserStorage {
    private HashMap<Long, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return Optional.of(users.get(id));
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return users
                .values()
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }
}
