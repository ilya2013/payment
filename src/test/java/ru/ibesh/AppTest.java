package ru.ibesh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.CreditCard;
import ru.ibesh.payment.Currency;
import ru.ibesh.storage.UserRepository;

import java.util.*;

@SpringBootTest
class AppTest {
    @Autowired
    UserRepository userRepository;

//    @BeforeEach
//    void setUp() {
//
//    }

    @Test
    public void whenLoginAndUserExistsThenGetId(){
        userRepository.deleteAll();

        userRepository.save(new User(null, "login1", "login1", "9114587", new HashSet<>(), new HashSet<>()));
        Card creditCard = new CreditCard("3454", 100, Currency.RUB, 10);
        Set<Card> cards = new HashSet<>();
        cards.add(creditCard);
        User user = new User(null, "login3", "login1", "9114587", cards, new HashSet<>());
        userRepository.save(user);
        System.out.println(userRepository.findById(1L).get().getLogin());
        System.out.println(userRepository.findById(2L).get().getCards().size());

//TODO Update test
    }
}