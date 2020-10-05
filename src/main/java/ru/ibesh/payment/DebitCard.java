package ru.ibesh.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ibesh.Identifiable;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class DebitCard implements PaymentInstrument {
    private final String cardNumber;
    private int balance;
    private final Currency currency;

    public DebitCard(String cardNumber, int balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.currency = Currency.RUB;
    }

    @Override
    public int availableMoney() {
        return balance;
    }

    @Override
    public void debitingFunds(int amount) {
        if (availableMoney() < amount) {
            throw new NotEnoughMoney(String.format("Недостаточно средств на карте %s: %d %s", cardNumber, amount, currency));
        }
        balance -= amount;
    }

    @Override
    public void addingFunds(int amount) {
        amount += amount;
    }

    @Override
    public boolean isEnough2Pay(int amount) {
        return balance >= amount;
    }

}
