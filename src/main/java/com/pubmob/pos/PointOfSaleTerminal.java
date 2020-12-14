package com.pubmob.pos;

import io.vavr.collection.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {

    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        BarcodeInventory barcodeInventory = new BarcodeInventory(
                HashMap.of("12345", 550, "23456", 1015));

        new BufferedReader(new InputStreamReader(System.in)).lines()
                .map(String::trim)
                .takeWhile(PointOfSaleTerminal::isNotQuitCommand)
                .map(barcode -> handleCommand(barcodeInventory, barcode))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static String handleCommand(BarcodeInventory barcodeInventory, String barcode) {
        if (barcode.equals("total")) {
            return "Total: $0.00";
        }
        return barcodeInventory.handleBarcode(barcode);
    }

    private static boolean isNotQuitCommand(String line) {
        return !"q".equals(line);
    }

}







