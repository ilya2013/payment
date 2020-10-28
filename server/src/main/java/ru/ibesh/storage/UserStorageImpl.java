package ru.ibesh.storage;

import ru.ibesh.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UserStorageImpl implements UserStorage {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    @Override
    public User addUser(User user) {
        Long id;
        if (user.getUserId() != null) {
            users.put(user.getUserId(), user);
        } else {
            id = nextId.getAndIncrement();
            user.setUserId(id);
            users.put(id, user);
        }
        return user;
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
