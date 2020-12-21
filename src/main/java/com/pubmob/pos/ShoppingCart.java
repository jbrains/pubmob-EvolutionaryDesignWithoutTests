package com.pubmob.pos;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShoppingCart {
    private final Map<String, Integer> inventory;
    private final List<Integer> prices;

    public ShoppingCart(Map<String, Integer> inventory) {
        this.inventory = inventory;
        this.prices = new ArrayList<>();
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    String getTotal() {
        String formatPrice = formatPrice(prices.stream().reduce(0, Integer::sum));
        this.prices.clear();
        return formatPrice;
    }

    public String handleBarcode(String barcode) {
        Optional<Integer> opPrice = Optional.ofNullable(inventory.get(barcode));

        opPrice.ifPresent(price -> addToCart(price));

        return opPrice
                .map(ShoppingCart::formatPrice)
                .orElse("Barcode not found: " + barcode + ".");
    }

    private boolean addToCart(Integer price) {
        return prices.add(price);
    }
}