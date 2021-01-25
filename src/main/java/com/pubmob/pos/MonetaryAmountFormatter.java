package com.pubmob.pos;

public class MonetaryAmountFormatter {
    public static String formatMonetaryAmount(final int cents) {
        return String.format("$%.2f", cents / 100d);
    }
}
