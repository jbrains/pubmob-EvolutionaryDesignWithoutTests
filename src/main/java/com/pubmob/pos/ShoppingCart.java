package com.pubmob.pos;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Integer> prices;

    public ShoppingCart() {
        this.prices = new ArrayList<>();
    }

    static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    String getTotal() {
        String formatPrice = formatPrice(prices.stream().reduce(0, Integer::sum));
        this.prices.clear();
        return formatPrice;
    }

    boolean addToCart(Integer price) {
        return prices.add(price);
    }
}