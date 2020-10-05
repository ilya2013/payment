package ru.ibesh.payment.handler;

import lombok.extern.log4j.Log4j2;
import ru.ibesh.payment.Payment;
import ru.ibesh.payment.Status;

import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class PaymentHandlerImpl implements PaymentHandler {
    private String ip = "127.0.0.1";
    private int port = 3020;
    private final AtomicInteger uid = new AtomicInteger();

    @Override
    public void init() {

    }

    @Override
    public Payment pay(Payment payment) {
        payment.setStatus(Status.SUCCESS);
        payment.setPaymentUID( ip + ":" + port + "_" + uid.getAndIncrement());
        log.info(String.format("Successful payment request: %s", payment));
        return payment;
    }
}
