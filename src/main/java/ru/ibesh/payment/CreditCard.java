package ru.ibesh.payment;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreditCard extends Card{
    @Column(name = "limit_")
    private int limit;
    private int usedLimit = 0;

//    protected String cardNumber;
//    protected int balance;
//    protected Currency currency;
//    protected User user;

    public CreditCard(String cardNumber, int balance, Currency currency, User user){
       super(cardNumber, balance, currency, user);
    }

    public CreditCard(String cardNumber, int balance, Currency currency, User user, int limit, int usedLimit) {
        super(cardNumber, balance, currency, user);
        this.limit = limit;
        this.usedLimit = usedLimit;
    }

    @Override
    public int availableMoney() {
        return super.getBalance() + limit - usedLimit;
    }

    @Override
    public void debitingFunds(int amount) {
        if (!isEnough2Pay(amount)){
            throw new NotEnoughMoney("Not enough money!");
        }
        if (super.getBalance()  >= amount){
            super.setBalance(super.getBalance() - amount);
        } else{
            usedLimit += amount - super.getBalance() ;
            super.setBalance(0);
        }
    }
    @Override
    public void addingFunds(int amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public boolean isEnough2Pay(int amount) {
        return availableMoney() >= amount;
    }

}
