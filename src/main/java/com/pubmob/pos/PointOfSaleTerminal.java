package com.pubmob.pos;

import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        ShoppingCart shoppingCart = new ShoppingCart(
                Map.of("12345", 550, "23456", 1015));

        new BufferedReader(new InputStreamReader(System.in)).lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .takeWhile(PointOfSaleTerminal::isNotQuitCommand)
                .map(barcode -> handleCommand(shoppingCart, barcode))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static String handleCommand(ShoppingCart shoppingCart, String command) {
        if ("total".equals(command)) {
            return "Total: %s".formatted(shoppingCart.getTotal());
        }
        return shoppingCart.handleBarcode(command);
    }

    private static boolean isNotQuitCommand(String command) {
        return !"q".equals(command);
    }
}