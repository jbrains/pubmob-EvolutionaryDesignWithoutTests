package com.pubmob.pos;

import io.vavr.collection.Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        ShoppingCart shoppingCart = new ShoppingCart(
                Map.of("12345", 550, "23456", 1015));

        Stream.ofAll(new BufferedReader(new InputStreamReader(System.in)).lines())
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .takeUntil(PointOfSaleTerminal::isQuitCommand)
                .map(barcode -> handleCommand(shoppingCart, barcode))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static String handleCommand(ShoppingCart shoppingCart, String command) {
        if ("total".equals(command)) {
            return String.format("Total: %s", shoppingCart.getTotal());
        }
        return shoppingCart.handleBarcode(command);
    }

    private static boolean isQuitCommand(String command) {
        return "q".equals(command);
    }
}
