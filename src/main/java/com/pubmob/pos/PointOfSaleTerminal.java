package com.pubmob.pos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        input.lines().takeWhile(line -> !"q".equals(line)).forEach(PointOfSaleTerminal::handleBarcode);

        System.out.println("Done.");
    }

    private static void handleBarcode(String barcode) {
        if ("12345".equals(barcode))
            System.out.println("$5.50");
        else
            System.out.println("Error: barcode not found.");
    }
}
