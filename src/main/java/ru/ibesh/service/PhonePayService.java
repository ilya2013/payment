package ru.ibesh.service;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;
import ru.ibesh.payment.User;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.Payment;
import ru.ibesh.payment.handler.PaymentHandler;
import ru.ibesh.payment.handler.PaymentHandlerImpl;
import ru.ibesh.service.check.PhoneChecker;

import java.security.InvalidParameterException;

@Log4j2
@Service
public class PhonePayService implements PayService {

    private final PhoneChecker phoneChecker = new PhoneChecker();
    private final PaymentHandler paymentHandler = new PaymentHandlerImpl();

    @Override
    public  Payment pay(User user, Card card, String purpose, int paySum) {
        if (!phoneChecker.isValidPhoneNumber(purpose)){
            throw new InvalidPaymentPurpose("Invalid phone number format: " + purpose);
        }
        try {
            card = user.findCardByCardNumber(card.getCardNumber()).orElseThrow(InvalidParameterException::new);
        }catch (Exception e){
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        Payment payment = new Payment(user, card, paySum);
        paymentHandler.pay(payment);
        user.getPayments().add(payment);
        return payment;
    }
}
