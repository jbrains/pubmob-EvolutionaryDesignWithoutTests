package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Integer> prices;

    public ShoppingCart() {
        this.prices = new ArrayList<>();
    }

    public boolean addItemPrice(final int price) {
        return prices.add(price);
    }

    public int calculateTotal() {
        return prices.stream().reduce(0, Integer::sum);
    }

    public void emptyShoppingCart() {
        prices.clear();
    }
}
