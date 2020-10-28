package ru.ibesh.payment.handler;

import ru.ibesh.payment.Payment;

public interface PaymentHandler {
    void init();
    Payment pay(Payment payment);
}
