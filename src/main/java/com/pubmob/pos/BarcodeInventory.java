package com.pubmob.pos;

import io.vavr.collection.HashMap;

public class BarcodeInventory {

    private HashMap<String, Integer> inventory;

    public BarcodeInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    public String handleBarcode(final String barcode) {
        return inventory.get(barcode)
                .map(BarcodeInventory::formatPrice)
                .getOrElse("Error: barcode not found.");
    }
}