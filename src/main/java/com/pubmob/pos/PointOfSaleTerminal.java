package com.pubmob.pos;

import io.vavr.collection.Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        // REFACTOR Inject formatting behavior into Controllers?
        Controllers controllers = new Controllers(
                new ProductCatalog(
                        Map.of(
                                "12345", new Product(550, false, true),
                                "23456", new Product(1015, true, false),
                                "34567", new Product(1000, true, true))),
                new PurchaseInProgress());

        Stream.ofAll(new BufferedReader(new InputStreamReader(System.in)).lines())
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .takeUntil(PointOfSaleTerminal::isQuitCommand)
                .map(barcode -> handleCommand(controllers, barcode))
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static String handleCommand(Controllers controllers, String command) {
        if ("total".equals(command)) {
            return controllers.handleTotal(command);
        }
        return controllers.handleBarcode(command);
    }

    private static boolean isQuitCommand(String command) {
        return "q".equals(command);
    }
}
