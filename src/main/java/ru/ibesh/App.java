package ru.ibesh;

import ru.ibesh.service.PayService;
import ru.ibesh.storage.UserStorage;
import ru.ibesh.ui.UserNotFound;

import java.util.List;

public class App {
    private UserStorage userStorage;
    private List<PayService> payOperations;

    public App(UserStorage userStorage, List<PayService> payOperations) {
        this.userStorage = userStorage;
        this.payOperations = payOperations;
    }
    public Long logIn(String login) {
        User user = userStorage.findUserByLogin(login).orElseThrow(UserNotFound::new);
        return user.getUserId();
    }
}
