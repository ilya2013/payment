package ru.ibesh.payment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Card implements PaymentInstrument{
    protected String cardNumber;
    protected int balance;
    protected Currency currency;
    protected User user;

}
