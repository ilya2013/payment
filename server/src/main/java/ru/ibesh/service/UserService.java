package ru.ibesh.service;

import org.springframework.stereotype.Service;
import ru.ibesh.User;
import ru.ibesh.payment.CreditCard;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.storage.UserStorage;
import ru.ibesh.storage.UserStorageImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserStorage userStorage = new UserStorageImpl();

    {
        PaymentInstrument creditCard = new CreditCard("3452234323", 100, Currency.RUB, 10, 0);
        Set<PaymentInstrument> paymentInstruments = new HashSet<>();
        paymentInstruments.add(creditCard);
        User user = new User(1L
                , "login1"
                , "psw1"
                , "9111786543"
                , paymentInstruments
                , new HashMap<>());
        userStorage.addUser(user);
    }

    public Optional<User> findByLogin(String login){
        return userStorage.findUserByLogin(login);
    }

    public Optional<PaymentInstrument> findCardByNumber(User user, String cardNumber){
        return user.getPaymentInstruments().stream()
                .filter(a -> a.isCard() && a.getUniqueNumber().equals(cardNumber))
                .findFirst();
    }

    public User addUser(User user){
        return userStorage.addUser(user);
    }
}
