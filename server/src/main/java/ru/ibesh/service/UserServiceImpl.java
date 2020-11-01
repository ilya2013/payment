package ru.ibesh.service;

import org.springframework.stereotype.Service;
import ru.ibesh.User;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.storage.UserStorage;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserStorage userStorage;

    public UserServiceImpl(UserStorage userStorage){
        this.userStorage = userStorage;
    }
    @Override
    public Optional<User> findByLogin(String login){
        return userStorage.findUserByLogin(login);
    }
    @Override
    public Optional<PaymentInstrument> findCardByNumber(User user, String cardNumber){
        return user.getPaymentInstruments().stream()
                .filter(a -> a.isCard() && a.getUniqueNumber().equals(cardNumber))
                .findFirst();
    }
    @Override
    public User addUser(User user){
        return userStorage.addUser(user);
    }
}
