package com.pubmob.pos;

public class MonetaryAmountFormatter {
    private final Formatter formatter = new Formatter();

    public String formatMonetaryAmount(final int cents) {
        return formatter.formatString("$%.2f", cents / 100d);
    }
}
