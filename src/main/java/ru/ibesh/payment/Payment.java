package ru.ibesh.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ibesh.User;

@Getter
@Setter
@ToString
public class Payment {
    private final User user;
    private final PaymentInstrument paymentInstrument;
    private final int paySum;
    private Status status;
    private String paymentUID;

    public Payment(User user, PaymentInstrument paymentInstrument, int paySum) {
        this.user = user;
        this.paymentInstrument = paymentInstrument;
        this.paySum = paySum;
        this.status = Status.WAIT;
    }
}
