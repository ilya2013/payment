package ru.ibesh.payment.manager;

import ru.ibesh.payment.Payment;
import ru.ibesh.payment.Status;

public class PaymentHandlerImpl implements PaymentHandler {
    private String ip;
    private int port;

    @Override
    public void init() {

    }

    @Override
    public Payment pay(Payment payment) {
        payment.setStatus(Status.SUCCESS);
        return payment;
    }
}
