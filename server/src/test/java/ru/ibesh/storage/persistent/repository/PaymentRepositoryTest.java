package ru.ibesh.storage.persistent.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.Status;
import ru.ibesh.storage.persistent.entity.Card;
import ru.ibesh.storage.persistent.entity.CreditCard;
import ru.ibesh.storage.persistent.entity.Payment;
import ru.ibesh.storage.persistent.entity.UserEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PaymentRepositoryTest {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentRepositoryTest(PaymentRepository paymentRepository, UserRepository userRepository){
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    private void setUp(){
        userRepository.deleteAll();
    }

    @Test
    void findBypaymentUID() {
        String expected = "q123";
        UserEntity userEntity1 = new UserEntity(null
                , "login1"
                , "login2"
                , "89122435689"
                , new ArrayList<Card>()
                , new ArrayList<Payment>());
        userEntity1 = userRepository.save(userEntity1);
        Card card = new CreditCard(null, "34543465234", 100, Currency.RUB, userEntity1, 10, 0);
        userEntity1.getPaymentInstruments().add(card);
        Payment payment = new Payment(null, userEntity1, card.getCardNumber(), 10, Status.SUCCESS, "q123");
        userEntity1.getPayments().add(payment);
        userEntity1 = userRepository.save(userEntity1);
        assertEquals(expected, paymentRepository.findByPaymentUID(payment.getPaymentUID()).get().getPaymentUID());
    }
}