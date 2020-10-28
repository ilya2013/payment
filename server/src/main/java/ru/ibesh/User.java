package ru.ibesh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import ru.ibesh.payment.Payment;
import ru.ibesh.payment.PaymentInstrument;

import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
@Log4j2
public class User {
    private Long userId;
    private String login;
    private String password;
    private String phoneNumber;
    private final Set<PaymentInstrument> paymentInstruments;
    private final Map<String, Payment> payments;

    public void addPaymentInstrument(PaymentInstrument paymentInstrument){
        paymentInstruments.add(paymentInstrument);
    }

    public void savePayment(Payment payment){
        log.debug("Сохранение платежа: {}; пользователю {}", payment, this);
        payments.put(payment.getPaymentUID(), payment);
        log.debug("Платеж: \"{}\" сохранён пользователю c логином \"{}\"", payment.getPaymentUID(), this.login);

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
