package com.pubmob.pos;

public class Product {
    private final int cents;
    private final boolean gstApplies;

    public Product(final int cents, final boolean gstApplies) {
        this.cents = cents;
        this.gstApplies = gstApplies;
    }

    public Product(int cents) {
        this(cents, false);
    }

    public int cost() {
        return inCents();
    }

    public String formatPrice() {
        final String gstApplied = gstApplies ? " G" : "";
        return String.format("$%.2f%s", cents / 100d, gstApplied);
    }

    public int inCents() {
        return cents;
    }
}
