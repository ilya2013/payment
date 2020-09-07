package ru.ibesh.payment.manager;

import ru.ibesh.payment.Payment;
import ru.ibesh.payment.Status;

public interface PaymentHandler {
    void init();
    Payment pay(Payment payment);
}
