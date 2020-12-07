package com.pubmob.pos;

import io.vavr.collection.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        new BufferedReader(new InputStreamReader(System.in))
                .lines()
                .takeWhile(line -> !"q".equals(line))
                .forEach(PointOfSaleTerminal::handleBarcode);

        System.out.println("Done.");
    }

    private static void handleBarcode(String barcode) {
        final HashMap<String, String> barcodeToPrice =
                HashMap.of("12345", formatPrice(550), "23456", formatPrice(1015));
        if (barcodeToPrice.containsKey(barcode))
            System.out.println(barcodeToPrice.getOrElse(barcode, null));
        else
            System.out.println("Error: barcode not found.");
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents/100d);
    }
}
