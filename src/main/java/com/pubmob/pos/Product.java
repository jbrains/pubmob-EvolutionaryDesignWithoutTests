package com.pubmob.pos;

public class Product {
    private final static int gstRateInPercentagePoints = 5;
    private final static int pstRateInPercentagePoints = 8;

    private final int netPrice;
    private final boolean gstApplies;
    private final boolean pstApplies;

    public Product(final int netPrice, final boolean gstApplies, boolean pstApplies) {
        this.netPrice = netPrice;
        this.gstApplies = gstApplies;
        this.pstApplies = pstApplies;
    }

    public int cost() {
        return netPrice()
            + (gstApplies ? salesTaxInCents(this.gstRateInPercentagePoints) : 0)
            + (pstApplies ? salesTaxInCents(this.pstRateInPercentagePoints) : 0);
    }

    private int salesTaxInCents(int percentagePoints) {
        return (int) Math.round(percentagePoints * 0.01d * netPrice());
    }

    public String formatPrice() {
        final String gstApplied = gstApplies ? " G" : "";
        final String pstApplied = pstApplies ? " P" : "";
        // REFACTOR Use formatMonetaryAmount() for this
        return String.format("$%.2f%s%s", netPrice / 100d, gstApplied, pstApplied);
    }

    public int netPrice() {
        return netPrice;
    }
}
