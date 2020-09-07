package ru.ibesh.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.ibesh.Identifiable;

@Getter
@Setter
@AllArgsConstructor
public class DebitCard implements PaymentInstrument {
    private String cardNumber;
    private int balance;
    private final Currency currency;

    public DebitCard(long id, int balance){
       this.balance = balance;
       this.currency = Currency.RUB;
    }

    @Override
    public int availableMoney() {
        return balance;
    }

    @Override
    public void debitingFunds(int amount) {
        if (balance - amount < 0){
            throw new NotEnoughMoney();
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
