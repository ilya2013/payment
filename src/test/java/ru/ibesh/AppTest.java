package ru.ibesh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ibesh.payment.CreditCard;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.service.PayService;
import ru.ibesh.service.PhonePayService;
import ru.ibesh.storage.UserStorage;
import ru.ibesh.storage.UserStorageImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenLoginAndUserExistsThenGetId(){
        PaymentInstrument creditCard = new CreditCard(100, Currency.RUB, 10);
        Set<PaymentInstrument> paymentInstruments = new HashSet<>();
        paymentInstruments.add(creditCard);
        User user1 = new User(1, "login1", "login1", "9114587", paymentInstruments, new HashMap<>());
        UserStorage userStorage = new UserStorageImpl();
        userStorage.addUser(user1);
        List<PayService> payServices = new ArrayList<>();
        payServices.add(new PhonePayService());
        App app = new App(userStorage, payServices);
        long expectedUserId = 1;
        assertEquals(expectedUserId, app.logIn("login1"));
    }
}