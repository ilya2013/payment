package ru.ibesh.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public  interface PaymentInstrument {
    int getBalance();
    int availableMoney();
    Currency getCurrency();
    void debitingFunds(int amount);
    void addingFunds(int amount);
    boolean isEnough2Pay(int amount);
}
