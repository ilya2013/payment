package ru.ibesh.service;

import ru.ibesh.User;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.Payment;

public interface PayService {
    Payment pay(User user, Card card, String purpose, int paySym);
}
