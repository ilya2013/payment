package ru.ibesh.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;
import ru.ibesh.User;
import ru.ibesh.payment.Payment;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.payment.handler.PaymentHandler;
import ru.ibesh.payment.handler.PaymentHandlerImpl;
import ru.ibesh.service.check.PhoneChecker;

@Log4j2
@NoArgsConstructor
@Service
public class PhonePayService implements PayService {
    private final PhoneChecker phoneChecker = new PhoneChecker();
    private final PaymentHandler paymentHandler = new PaymentHandlerImpl();

    public  Payment pay(User user, PaymentInstrument paymentInstrument, String purpose, int paySum) {
        if (!phoneChecker.isValidPhoneNumber(purpose)){
            throw new InvalidPaymentPurpose("Invalid phone number format: " + purpose);
        }
        try {
            paymentInstrument.debitingFunds(paySum);
        }catch (Exception e){
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        Payment payment = new Payment(user, paymentInstrument, paySum);
        paymentHandler.pay(payment);
        user.savePayment(payment);
        return payment;
    }
}
