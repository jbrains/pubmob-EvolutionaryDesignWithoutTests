package com.pubmob.pos;

public class TotalFormatter {
    // REFACTOR Eventually formatMonetaryAmount()
    public static String formatTotal(final int cents) {
        return String.format("$%.2f", cents / 100d);
    }
}
