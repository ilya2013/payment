package ru.ibesh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ibesh.service.PayService;

@Controller
@RequestMapping("/phonePayment")
public class PhonePaymentController {
    private final PayService payService;

    public PhonePaymentController(PayService payService){
        this.payService = payService;
    }
    @PostMapping("")
    public ResponseEntity<String> pay(Pe){
        payService.
        return new ResponseEntity<>("s", HttpStatus.OK);
    }
}
