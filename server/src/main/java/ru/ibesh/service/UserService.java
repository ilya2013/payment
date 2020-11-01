package ru.ibesh.service;

import ru.ibesh.User;
import ru.ibesh.payment.PaymentInstrument;

import java.util.Optional;

public interface UserService {
    Optional<User> findByLogin(String login);

    Optional<PaymentInstrument> findCardByNumber(User user, String cardNumber);

    User addUser(User user);
}
