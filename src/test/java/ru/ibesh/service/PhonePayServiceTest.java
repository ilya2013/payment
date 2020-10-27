package ru.ibesh.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ibesh.User;
import ru.ibesh.payment.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhonePayServiceTest {
    private final Map<Integer, User> users = new HashMap<>();

    @BeforeEach
    private void createUsers() {
        PaymentInstrument paymentInstrument = new DebitCard("1", 100);
        Set<PaymentInstrument> paymentInstruments = new HashSet<>();
        paymentInstruments.add(paymentInstrument);
        users.put(1, new User(1L, "1", "1", "123", paymentInstruments, new HashMap<>()));
    }

    @AfterEach
    private void clearUsers() {
        users.clear();
    }

    @Test
    void pay() {
        PaymentInstrument paymentInstrument = users.get(1)
                .getPaymentInstruments()
                .stream()
                .findFirst()
                .get();
        Status expected = Status.SUCCESS;
        Payment payment = new PhonePayService().pay(users.get(1), paymentInstrument, "8 911 176 60 54", 90);
        assertEquals(expected, payment.getStatus());
    }

    @Test
    void WhenNotRussianPhoneNumberThenThrowsNotEnoughMoney() {
        PaymentInstrument paymentInstrument = users.get(1)
                .getPaymentInstruments()
                .stream()
                .findFirst()
                .get();
        Assertions.assertThrows(NotEnoughMoney.class, () -> new PhonePayService().pay(users.get(1), paymentInstrument, "8 911 176 60 54", 190));
    }

    @Test
    void WhenNotRussianPhoneNumberThenThrowsInvalidPaymentPurpose() {
        PaymentInstrument paymentInstrument = users.get(1)
                .getPaymentInstruments()
                .stream()
                .findFirst()
                .get();
        Assertions.assertThrows(InvalidPaymentPurpose.class, () -> new PhonePayService().pay(users.get(1), paymentInstrument, "+361 911 176 60 54", 190));
    }
}