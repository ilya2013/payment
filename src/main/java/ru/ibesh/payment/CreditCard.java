package ru.ibesh.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ibesh.Identifiable;

@Getter
@Setter
@ToString
public class CreditCard implements PaymentInstrument{
    private String cardNumber;
    private int balance;
    private Currency currency;
    private int limit;
    private int usedLimit = 0;

    public CreditCard(int balance, Currency currency) {
        this.balance = balance;
        this.currency = currency;
    }

    public CreditCard(int balance, Currency currency, int limit){
        this(balance, currency);
        this.limit = limit;
    }
    @Override
    public int availableMoney() {
        return balance + limit - usedLimit;
    }

    @Override
    public void debitingFunds(int amount) {
        if (balance + (limit - usedLimit) < amount){
            throw new NotEnoughMoney("Not enough money!");
        }
        if (balance >= amount){
            balance -= amount;
        } else{
            usedLimit += amount - balance;
            balance = 0;
        }
    }
    @Override
    public void addingFunds(int amount) {
        balance += amount;
    }

    @Override
    public boolean isEnough2Pay(int amount) {
        return balance + (limit - usedLimit) >= amount;
    }
}
