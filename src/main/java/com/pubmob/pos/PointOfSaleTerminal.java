package com.pubmob.pos;

import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        Map<String, Integer> inventory = Map.of("12345", 550, "23456", 1015);
        ShoppingCart shoppingCart = new ShoppingCart(
        );

        new BufferedReader(new InputStreamReader(System.in)).lines()
                .map(String::trim)
                .takeWhile(PointOfSaleTerminal::isNotQuitCommand)
                .map(barcode -> handleCommand(shoppingCart, barcode, inventory))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static String handleCommand(ShoppingCart shoppingCart, String command, Map<String, Integer> inventory) {
        if (command.equals("total")) {
            return "Total: " +
                    shoppingCart.getTotal();
        }
        return handleBarcode(shoppingCart, command, inventory);
    }

    private static boolean isNotQuitCommand(String command) {
        return !"q".equals(command);
    }

    public static String handleBarcode(ShoppingCart shoppingCart, String barcode, Map<String, Integer> inventory) {
        Optional<Integer> opPrice = Optional.ofNullable(inventory.get(barcode));

        opPrice.ifPresent(price -> shoppingCart.addToCart(price));

        return opPrice
                .map(ShoppingCart::formatPrice)
                .orElse("Barcode not found: " + barcode + ".");
    }
}