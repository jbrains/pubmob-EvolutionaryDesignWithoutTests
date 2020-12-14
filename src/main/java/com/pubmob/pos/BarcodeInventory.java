package com.pubmob.pos;

import io.vavr.collection.List;
import io.vavr.collection.Map;

public class BarcodeInventory {

    private Map<String, Integer> inventory;
    private List<Integer> prices;

    public BarcodeInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
        this.prices = List.empty();
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    String getTotal() {
        return formatPrice(0);
    }

    public String handleBarcode(final String barcode) {
        return inventory.get(barcode)
                .map(BarcodeInventory::formatPrice)
                .getOrElse("Error: barcode not found.");
    }
}