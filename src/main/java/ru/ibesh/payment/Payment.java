package ru.ibesh.payment;

import lombok.Getter;
import lombok.Setter;
import ru.ibesh.User;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.payment.Status;

@Getter
@Setter
public class Payment {
    private final User user;
    private final PaymentInstrument paymentInstrument;
    private final int paySum;
    private Status status;

    public Payment(User user, PaymentInstrument paymentInstrument, int paySum) {
        this.user = user;
        this.paymentInstrument = paymentInstrument;
        this.paySum = paySum;
        this.status = Status.WAIT;
    }
}
