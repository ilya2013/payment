package ru.ibesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ibesh.User;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.CreditCard;
import ru.ibesh.payment.Currency;
import ru.ibesh.service.PayService;
import ru.ibesh.storage.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/phonePayment")
public class PhonePaymentController {
    private final PayService payService;
    @Autowired
    UserRepository userRepository;

    public PhonePaymentController(PayService payService){
        this.payService = payService;
    }
    @PostMapping("")
    public ResponseEntity<String> pay(){
        Card creditCard = new CreditCard("3454", 100, Currency.RUB, 10);
        Set<Card> cards = new HashSet<>();
        cards.add(creditCard);
        User user = new User(null, "login1", "login1", "9114587", cards, new HashSet<>());
        creditCard.setUser(user);
        userRepository.save(user);
        payService.pay(user, creditCard, "89111766105", 10);
        return new ResponseEntity<>("s", HttpStatus.OK);
    }
}
