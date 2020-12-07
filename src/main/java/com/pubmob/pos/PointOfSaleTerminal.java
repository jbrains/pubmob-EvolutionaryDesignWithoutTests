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
        final HashMap<String, Integer> barcodeToPrice = HashMap.of("12345", 550, "23456", 1015);

        System.out.println(
                barcodeToPrice.get(barcode)
                        .map(PointOfSaleTerminal::formatPrice)
                        .getOrElse("Error: barcode not found."));
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }
}
