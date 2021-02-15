package com.pubmob.pos;

public class MonetaryAmountFormatter {
    private final Formatter formatter;

    public MonetaryAmountFormatter(final Formatter formatter) {
        this.formatter = formatter;
    }

    public String formatMonetaryAmount(final int cents) {
        return formatter.formatString("$%.2f", cents / 100d);
    }
}
