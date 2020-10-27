package ru.ibesh.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ibesh.payment.User;
import ru.ibesh.payment.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class PhonePayServiceTest {
    @Autowired
    PhonePayService phonePayService;
    private final Map<Integer, User> users = new HashMap<>();
    private final Set<Card> cards = new HashSet<>();

    @BeforeEach
    private void createUsers() {
        Card card = new DebitCard("1", 100, Currency.RUB);
        cards.add(card);
        users.put(1, new User(1L, "1", "1", "123", cards, new HashSet<>()));
    }

    @AfterEach
    private void clearUsers() {
        users.clear();
    }

//    @Test
//    void pay() {
//        Card card = users.get(1)
//                .getCards()
//                .stream()
//                .findFirst()
//                .get();
//        Status expected = Status.SUCCESS;
//        Payment payment = phonePayService.pay(users.get(1), card, "8 911 176 60 54", 90);
//        assertEquals(expected, payment.getStatus());
//    }

    @Test
    void WhenNotRussianPhoneNumberThenThrowsNotEnoughMoney() {
        Card card = users.get(1)
                .getCards()
                .stream()
                .findFirst()
                .get();
        Assertions.assertThrows(NotEnoughMoney.class, () -> phonePayService.pay(users.get(1), card, "8 911 176 60 54", 190));
    }

    @Test
    void WhenNotRussianPhoneNumberThenThrowsInvalidPaymentPurpose() {
        Card card = users.get(1)
                .getCards()
                .stream()
                .findFirst()
                .get();
        Assertions.assertThrows(InvalidPaymentPurpose.class, () -> phonePayService.pay(users.get(1), card, "+361 911 176 60 54", 190));
    }
}