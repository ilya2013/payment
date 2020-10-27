package ru.ibesh.payment;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardModelTest {
    @Test
    @DisplayName("Not enough modeney -> NotEnoughMoneyException")
    public void whenNotEnoghMoneyThenThrowsNotEnoughMoneyException(){
        Exception exception = assertThrows(NotEnoughMoney.class, () -> {
            PaymentInstrument paymentInstrument = new CreditCard();
            paymentInstrument.debitingFunds(160);
        });
        String expectedMessage = "Not enough money!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenUsesCreditCardLimitsThenTheyGettingLower(){
//        PaymentInstrument paymentInstrument = new CreditCard("4523", 100, Currency.RUB, 50);
        PaymentInstrument paymentInstrument = new CreditCard();
        System.out.println(paymentInstrument.getBalance());
        assertEquals(100,paymentInstrument.getBalance());
        paymentInstrument.debitingFunds(140);
        assertEquals(0,paymentInstrument.getBalance());
        System.out.println(paymentInstrument.availableMoney());
        assertEquals(10,paymentInstrument.availableMoney());
    }
}