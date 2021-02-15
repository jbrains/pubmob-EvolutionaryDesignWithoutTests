package com.pubmob.pos;

public class Formatter {
    public String formatString(final String template, final String... args) {
        return String.format(template, args);
    }
}