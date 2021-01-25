package com.pubmob.pos;

public class TotalFormatter {
    public static String formatTotal(final int cents) {
        return String.format("$%.2f", cents / 100d);
    }
}
