package com.pubmob.pos;

public class Formatter {
    public String formatString(final String template, final Object... args) {
        return String.format(template, args);
    }
}