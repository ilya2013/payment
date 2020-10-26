package ru.ibesh.service;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;
import ru.ibesh.User;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.CreditCard;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.Payment;
import ru.ibesh.payment.handler.PaymentHandler;
import ru.ibesh.payment.handler.PaymentHandlerImpl;
import ru.ibesh.service.check.PhoneChecker;
import ru.ibesh.storage.UserRepository;

import java.security.InvalidParameterException;
import java.util.HashSet;

@Log4j2
@Service
public class PhonePayService implements PayService {

    private final UserRepository userRepository;
    private final PhoneChecker phoneChecker = new PhoneChecker();
    private final PaymentHandler paymentHandler = new PaymentHandlerImpl();

    public PhonePayService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public  Payment pay(User user, Card card, String purpose, int paySum) {
        if (!phoneChecker.isValidPhoneNumber(purpose)){
            throw new InvalidPaymentPurpose("Invalid phone number format: " + purpose);
        }
        try {
            //TODO Подумать над более правильно ошибкой/создать свою
            user = userRepository.findById(user.getId()).orElseThrow(InvalidParameterException::new);
            card = user.findCardByCardNumber(card.getCardNumber()).orElseThrow(InvalidParameterException::new);
        }catch (Exception e){
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        Payment payment = new Payment(user, card, paySum);
        paymentHandler.pay(payment);
        user.getPayments().add(payment);
        userRepository.save(user);
        System.out.println(userRepository.findById(1L).orElse(new User()).getPayments().size());
        return payment;
    }

    public void createUser(){
        userRepository.deleteAll();
        User user = new User(1L, "login1", "login1", "9114587", new HashSet<>(), new HashSet<>());
        Card creditCard = new CreditCard(null, "4533", 100, Currency.RUB, user, 100, 0);
        user.getCards().add(creditCard);
        userRepository.save(user);
        System.out.println(userRepository.findById(1L));
    }
}
