package ru.ibesh;

import lombok.Value;

@Value
public class PaymentRq {
    private final String rqUID;
    private final String userLogin;
    private final String cardNumber;
    private final String purpose;
    private final Integer sum;
}
