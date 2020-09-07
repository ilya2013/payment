package ru.ibesh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ibesh.payment.CreditCard;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.service.Pay;
import ru.ibesh.service.PhonePay;
import ru.ibesh.storage.UserStorage;
import ru.ibesh.storage.UserStorageImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SbolTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenLoginAndUserExistsThenGetId(){
        PaymentInstrument creditCard = new CreditCard(100, Currency.RUB, 10);
        Set<PaymentInstrument> paymentInstruments = new HashSet<>();
        paymentInstruments.add(creditCard);
        User user1 = new User(1, "login1", "login1", "9114587", paymentInstruments);
        UserStorage userStorage = new UserStorageImpl();
        userStorage.addUser(user1);
        List<Pay> pays = new ArrayList<Pay>();
        pays.add(new PhonePay());
        Sbol sbol = new Sbol(userStorage, pays);
        long expectedUserId = 1;
        assertEquals(expectedUserId, sbol.logIn("login1"));
    }
}