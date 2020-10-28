package ru.ibesh.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ibesh.PaymentRq;
import ru.ibesh.PaymentRs;
import ru.ibesh.PaymentStatus;
import ru.ibesh.User;
import ru.ibesh.payment.Payment;
import ru.ibesh.payment.PaymentInstrument;
import ru.ibesh.payment.Status;
import ru.ibesh.service.PhonePayService;
import ru.ibesh.service.UserService;

import javax.smartcardio.Card;

@RestController
@RequestMapping("/PhonePay")
@Log4j2
@AllArgsConstructor
public class PhonePayController {
    private final PhonePayService  phonePayService;
    private final UserService  userService;


    @PostMapping("")
    public PaymentRs pay(@RequestBody PaymentRq paymentRq){
        log.info(String.format("Сервер. Получен PaymentRq: RqUID %s, UserLogin %s, CardNumber %s, PhoneNumber %s, Sum %s", paymentRq.getRqUID()
                , paymentRq.getUserLogin()
                , paymentRq.getCardNumber()
                , paymentRq.getPurpose()
                , paymentRq.getSum()));
        User user = findUser(paymentRq.getUserLogin());
        PaymentInstrument paymentInstrument = findCard(user, paymentRq.getCardNumber());
        Payment payment = phonePayService.pay(user, paymentInstrument, paymentRq.getPurpose(), paymentRq.getSum());
        return new PaymentRs(paymentRq.getRqUID(), mapStatus(payment.getStatus()), payment.getPaymentUID());
    }

    private User findUser(String login){
        return userService.findByLogin(login).orElseThrow(() -> new WrongParameterException("Неверный логин: " + login));
    }

    private PaymentInstrument findCard(User user, String cardNumber){
        return userService.findCardByNumber(user, cardNumber).orElseThrow(() -> new WrongParameterException("Неверный номер карты: " + cardNumber));
    }

    private PaymentStatus mapStatus(Status status){
        PaymentStatus paymentStatus = PaymentStatus.ERROR;
        if (status.equals(Status.SUCCESS)) {
            paymentStatus = PaymentStatus.SUCCESS;
        } else if (status.equals(Status.WAIT)){
            paymentStatus = PaymentStatus.WAIT;
        }
        return paymentStatus;
    }
}
