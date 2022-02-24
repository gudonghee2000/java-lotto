package model;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public int generatePurchaseCount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
