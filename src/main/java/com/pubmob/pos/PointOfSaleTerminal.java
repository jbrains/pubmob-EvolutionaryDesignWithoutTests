package com.pubmob.pos;

import io.vavr.collection.Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        // REFACTOR Inject Formatter into Controllers?
        Controllers controllers = new Controllers(
                new ProductCatalog(
                        Map.ofEntries(
                                catalogItem("12345", new Product(550, false, true)),
                                catalogItem("23456", new Product(1015, true, false)),
                                catalogItem("34567", new Product(1000, true, true)))),
                new ShoppingSession());

        Stream.ofAll(new BufferedReader(new InputStreamReader(System.in)).lines())
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .takeUntil(PointOfSaleTerminal::isQuitCommand)
                .map(barcode -> handleCommand(controllers, barcode))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static Map.Entry<String, Product> catalogItem(final String barcode, final Product product) {
        return Map.entry(barcode, product);
    }

    private static String handleCommand(Controllers controllers, String command) {
        if ("total".equals(command)) {
            return controllers.handleTotal(command);
        }
        else if ("receipt".equals(command)) {
            return controllers.handleReceipt(command);
        }
        return controllers.handleBarcode(command);
    }

    private static boolean isQuitCommand(String command) {
        return "q".equals(command);
    }
}
