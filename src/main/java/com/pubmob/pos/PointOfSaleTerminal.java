package com.pubmob.pos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {

    public static void main(String[] args) {
        System.out.println("Point of Sale Terminal.");

        new BufferedReader(new InputStreamReader(System.in)).lines()
                .map(String::trim)
                .takeWhile(PointOfSaleTerminal::isNotQuitCommand)
                .map(BarcodeInventory::handleBarcode)
                .forEach(System.out::println);

        System.out.println("Done.");
    }

    private static boolean isNotQuitCommand(String line) {
        return !"q".equals(line);
    }

}







