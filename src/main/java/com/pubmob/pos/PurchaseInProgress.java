package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInProgress {
    private final List<Product> products;

    public PurchaseInProgress() {
        this.products = new ArrayList<>();
    }

    public boolean addItemPrice(Product product) {
        return products.add(product);
    }

    private int calculateTotal() {
        return products.stream().map(Product::cost).reduce(0, Integer::sum);
    }

    private void reset() {
        products.clear();
    }

    public int finishPurchase() {
        final int totalInCents = calculateTotal();
        reset();
        return totalInCents;
    }
}
