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
        return netPrice() + salesTaxes();
    }

    private int salesTaxes() {
        return (gstApplies ? salesTaxInCents(this.gstRateInPercentagePoints) : 0)
            + (pstApplies ? salesTaxInCents(this.pstRateInPercentagePoints) : 0);
    }

    private int salesTaxInCents(int percentagePoints) {
        return (int) Math.round(percentagePoints * 0.01d * netPrice());
    }

    public String formatPrice() {
        final String anyTaxApplied = textIfApplies(gstApplies || pstApplies, " ");
        final String gstApplied = textIfApplies(gstApplies, "G");
        final String pstApplied = textIfApplies(pstApplies, "P");
        // REFACTOR Use formatMonetaryAmount() for this
        return String.format("$%.2f%s%s%s", netPrice / 100d, anyTaxApplied, gstApplied, pstApplied);
    }

    // SMELL I don't like this function name
    private String textIfApplies(final boolean applies, final String text) {
        return applies ? text : "";
    }

    public int netPrice() {
        return netPrice;
    }
}
