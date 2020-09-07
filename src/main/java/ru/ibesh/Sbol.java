package ru.ibesh;

import ru.ibesh.service.Pay;
import ru.ibesh.storage.UserStorage;
import ru.ibesh.storage.UserStorageImpl;
import ru.ibesh.ui.UserNotFound;

import java.util.ArrayList;
import java.util.List;

public class Sbol {
    private UserStorage userStorage;
    private List<Pay> payOperations;

    public Sbol(UserStorage userStorage, List<Pay> payOperations) {
        this.userStorage = userStorage;
        this.payOperations = payOperations;
    }
    public Long logIn(String login) {
        User user = userStorage.findUserByLogin(login).orElseThrow(UserNotFound::new);
        return user.getUserId();
    }
}
