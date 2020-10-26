package ru.ibesh.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
@Getter
@Setter
@AllArgsConstructor
@Entity
public class DebitCard extends Card{

    public DebitCard(String cardNumber, int balance, Currency currency) {
        super(cardNumber, balance, currency);
    }

    @Override
    public int availableMoney() {
        return getBalance();
    }

    @Override
    public void debitingFunds(int amount) {
        if (!isEnough2Pay(amount)) {
            throw new NotEnoughMoney(String.format("Недостаточно средств на карте %s: %d %s", getCardNumber(), amount, getCurrency()));
        }
         setBalance(availableMoney() - amount);
    }

    @Override
    public void addingFunds(int amount) {
        setBalance( availableMoney() + amount);
    }

    @Override
    public boolean isEnough2Pay(int amount) {
        return availableMoney() >= amount;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", user_id=" + user.getId() +
                '}';
    }
}
