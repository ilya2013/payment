package ru.ibesh.service;

import ru.ibesh.User;
import ru.ibesh.payment.PaymentInstrument;

public interface PayService {
    static boolean pay(User user, PaymentInstrument paymentInstrument, String purpose) {
        return false;
    }
}
