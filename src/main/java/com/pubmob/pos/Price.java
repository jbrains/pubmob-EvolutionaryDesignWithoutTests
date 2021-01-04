package com.pubmob.pos;

public class Price {

    private final int cents;
    private final boolean gstApplies;

    public Price(final int cents, final boolean gstApplies) {
        this.cents = cents;
        this.gstApplies = gstApplies;
    }

    public Price(int cents) {
        this(cents, false);
    }

    public String formatPrice() {
        final String gstApplied = gstApplies ? " G" : "";
        return String.format("$%.2f%s", cents / 100d, gstApplied);
    }

    public int inCents() {
        return cents;
    }
}
