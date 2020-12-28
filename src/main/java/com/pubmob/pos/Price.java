package com.pubmob.pos;

public class Price {
    private final int cents;

    public Price(int cents) {
        this.cents = cents;
    }

    public String formatPrice() {
        return String.format("$%.2f", cents / 100d);
    }

    public int inCents() {
        return cents;
    }
}
