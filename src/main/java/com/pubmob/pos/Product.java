package com.pubmob.pos;

public class Product {
    private final int gstRateInPercentagePoints = 5;

    private final int netPrice;
    private final boolean gstApplies;

    public Product(final int netPrice, final boolean gstApplies) {
        this.netPrice = netPrice;
        this.gstApplies = gstApplies;
    }

    public Product(int netPrice) {
        this(netPrice, false);
    }

    public int cost() {
        return netPrice() + (gstApplies ? salesTaxInCents() : 0);
    }

    private int salesTaxInCents() {
        return (int) Math.round(this.gstRateInPercentagePoints * 0.01d * netPrice());
    }

    public String formatPrice() {
        final String gstApplied = gstApplies ? " G" : "";
        // REFACTOR Use formatMonetaryAmount() for this
        return String.format("$%.2f%s", netPrice / 100d, gstApplied);
    }

    public int netPrice() {
        return netPrice;
    }
}
