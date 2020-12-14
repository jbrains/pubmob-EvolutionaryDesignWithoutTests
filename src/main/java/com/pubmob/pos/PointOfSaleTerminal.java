package com.pubmob.pos;

import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        BarcodeInventory barcodeInventory = new BarcodeInventory(
                Map.of("12345", 550, "23456", 1015));

        new BufferedReader(new InputStreamReader(System.in)).lines()
                .map(String::trim)
                .takeWhile(PointOfSaleTerminal::isNotQuitCommand)
                .map(barcode -> handleCommand(barcodeInventory, barcode))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static String handleCommand(BarcodeInventory barcodeInventory, String command) {
        if (command.equals("total")) {
            return "Total: " +
                    barcodeInventory.getTotal();
        }
        return barcodeInventory.handleBarcode(command);
    }

    private static boolean isNotQuitCommand(String command) {
        return !"q".equals(command);
    }
}