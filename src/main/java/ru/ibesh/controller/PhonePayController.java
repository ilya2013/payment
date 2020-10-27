package ru.ibesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PhonePay")
public class PhonePayController {

    @PostMapping("")
    public String pay(){
        return "";
    }
}
