package com.pubmob.pos;

public class Price {
    private final int price;

    public Price(int price) {
        this.price = price;
    }

    public String formatPrice() {
        return String.format("$%.2f", price / 100d);
    }

    public int getPrice() {
        return price;
    }
}
