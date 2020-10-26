package ru.ibesh.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ibesh.User;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard extends Card{
    @Column(name = "limit_")
    private int limit;
    private int usedLimit = 0;

    public CreditCard(String cardNumber,int balance, Currency currency) {
        super(cardNumber, balance, currency);
    }

    public CreditCard(String cardNumber,int balance, Currency currency, int limit){
        this(cardNumber,balance, currency);
        this.limit = limit;
    }

    public CreditCard(Long id, String cardNumber, int balance, Currency currency, User user_id, int limit, int usedLimit) {
        super(id, cardNumber, balance, currency, user_id);
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
