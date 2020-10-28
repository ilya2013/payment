package ru.ibesh.payment;

public  interface PaymentInstrument {
    int getBalance();
    int availableMoney();
    Currency getCurrency();
    void debitingFunds(int amount);
    void addingFunds(int amount);
    boolean isEnough2Pay(int amount);
    boolean isCard();
    String getUniqueNumber();
}
