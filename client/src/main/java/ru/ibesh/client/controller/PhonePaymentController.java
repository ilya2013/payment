package ru.ibesh.client.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.ibesh.PaymentRq;
import ru.ibesh.PaymentRs;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@Log4j2
@RequestMapping("/PhonePayment")
public class PhonePaymentController {
    private final AtomicInteger rqUIDSalt= new AtomicInteger();
    @GetMapping("")
    public ResponseEntity<String> getHelloMessage(){
        return new ResponseEntity<>("Hi!", HttpStatus.OK);
    }

    @PostMapping("/clients/{clientLogin}/cards/{cardNumber}/{phone}/{sum}")
    public ResponseEntity<String> pay(@PathVariable String clientLogin,
                         @PathVariable String cardNumber,
                         @PathVariable String phone,
                         @PathVariable int sum){
        log.info(String.format("Запрос на оплату клиента: clientLogin %s, cardNumber %s, phone %s, sum %s"
                , clientLogin
                , cardNumber
                , phone
                , sum));

        HttpEntity<PaymentRq> request = new HttpEntity<>(
                new PaymentRq(LocalDateTime.now() + Integer.toString(rqUIDSalt.incrementAndGet()),
                        clientLogin, cardNumber, phone, sum ));
        log.info(String.format("Запрос на оплату: %s", request.getBody()));
        ResponseEntity<PaymentRs> responseEntity = new RestTemplate()
                .postForEntity("http://127.0.0.1:8080/PhonePay"
                        , request
                        , PaymentRs.class);
        log.info("PaymentRs: getRqUID {}, Status {}, PaymentUID {}"
                , responseEntity.getBody().getRqUID()
                , responseEntity.getBody().getStatus()
                , responseEntity.getBody().getPaymentUID());

        return new ResponseEntity<>(responseEntity.getBody().toString(), HttpStatus.OK);
    }
}
