package com.pubmob.pos;

public class Product {
    private final static int gstRateInPercentagePoints = 5;
    private final static int pstRateInPercentagePoints = 8;

    private final int netPrice;
    private final boolean gstApplies;
    private final boolean pstApplies;
    private final Formatter formatter = new Formatter();

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
        final String formattedNetPrice = new MonetaryAmountFormatter(new Formatter()).formatMonetaryAmount(this.netPrice);
        final String anyTaxApplied = textIfApplies(gstApplies || pstApplies, " ");
        final String gstApplied = textIfApplies(gstApplies, "G");
        final String pstApplied = textIfApplies(pstApplies, "P");
        return formatter.formatString("%s%s%s%s", formattedNetPrice, anyTaxApplied, gstApplied, pstApplied);
    }

    // SMELL I don't like this function name
    private String textIfApplies(final boolean applies, final String text) {
        return applies ? text : "";
    }

    public int netPrice() {
        return netPrice;
    }
}
