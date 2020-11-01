package ru.ibesh.storage.persistent.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.Status;
import ru.ibesh.storage.persistent.entity.CreditCard;
import ru.ibesh.storage.persistent.entity.Card;
import ru.ibesh.storage.persistent.entity.Payment;
import ru.ibesh.storage.persistent.entity.UserEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }

//    @Test
//    public void saveUserWithIdWithoutAssotiations(){
//        User user = new User(7L
//                ,"login1"
//                , "login2"
//                , "89122435687"
//                , new ArrayList<Card>()
//                , new ArrayList<Payment>());
//        user = userRepository.save(user);
//        assertEquals("2login1", user.getId() + user.getLogin());
//    }


    @Test
    public void saveUserWithoutIdWithoutAssoitiations(){
        UserEntity userEntity = new UserEntity(null
                ,"login1"
                , "login2"
                , "89122435687"
                , new ArrayList<Card>()
                , new ArrayList<Payment>());
        userEntity = userRepository.save(userEntity);
        userEntity = userRepository.findById(userEntity.getId()).get();
        assertNotEquals(null, userEntity.getId());
    }

    @Test
    public void saveAndUpdateUserWithoutAssoitiations(){
        String expected = "login1_1";
        UserEntity userEntity = new UserEntity(null
                ,"login1"
                , "login2"
                , "89122435687"
                , new ArrayList<Card>()
                , new ArrayList<Payment>());
        userEntity = userRepository.save(userEntity);
        userEntity.setLogin(userEntity.getLogin() + "_1");
        userEntity = userRepository.save(userEntity);
        userEntity = userRepository.findById(userEntity.getId()).get();
        assertEquals(expected, userEntity.getLogin());
    }

    @Test
    public void save2UsersWithoutAssoitiations() {
        int expected = 2;
        UserEntity userEntity1 = new UserEntity(null
                , "login1"
                , "login2"
                , "89122435687"
                , new ArrayList<Card>()
                , new ArrayList<Payment>());
        UserEntity userEntity2 = new UserEntity(null
                , "login1"
                , "login2"
                , "89122435687"
                , new ArrayList<Card>()
                , new ArrayList<Payment>());
        userEntity1 = userRepository.save(userEntity1);
        userEntity1.setLogin(userEntity1.getLogin() + "_1");
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);
        assertEquals(expected, userRepository.count());
    }

    @Test
    public void saveUserWithAssoitiations() {
        int expected = 1;
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
        assertEquals(expected, userEntity1.getPaymentInstruments().size());
    }
}