package ru.ibesh.payment;

public class NotEnoughMoney extends RuntimeException {
    public NotEnoughMoney() {
        super();
    }
    public NotEnoughMoney(String notEnoughMoney) {
        super(notEnoughMoney);
    }
}
