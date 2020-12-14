package com.pubmob.pos;

import io.vavr.collection.HashMap;

public class BarcodeInventory {
    public static final HashMap<String, Integer> INVENTORY = HashMap.of("12345", 550, "23456", 1015);

    public static String handleBarcode(final String barcode) {
        return INVENTORY.get(barcode)
                .map(BarcodeInventory::formatPrice)
                .getOrElse("Error: barcode not found.");
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }
}