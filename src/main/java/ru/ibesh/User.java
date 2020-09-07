package ru.ibesh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ibesh.payment.PaymentInstrument;
import java.util.Set;

@Getter
@AllArgsConstructor
public class User {
    private long userId;
    private String login;
    private String password;
    private String phoneNumber;
    private Set<PaymentInstrument> paymentInstruments;

    public void addPaymentInstrument(PaymentInstrument paymentInstrument){
        paymentInstruments.add(paymentInstrument);
    }
}
