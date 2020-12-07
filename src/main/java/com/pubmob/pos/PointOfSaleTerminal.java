package com.pubmob.pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PointOfSaleTerminal {
    public static void main(String[] args) throws IOException {
        System.out.println("Point of Sale Terminal.");

        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final String barcode = input.readLine();
        if ("12345".equals(barcode))
            System.out.println("$5.50");
        else
            System.out.println("Error: barcode not found.");

        System.out.println("Done.");
    }
}
