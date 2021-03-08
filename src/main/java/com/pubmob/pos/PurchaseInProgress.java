package com.pubmob.pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchaseInProgress {
    private final List<Product> items;

    public PurchaseInProgress() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Product item) {
        return items.add(item);
    }

    private int calculateTotal() {
        return items.stream().map(Product::cost).reduce(0, Integer::sum);
    }

    private void reset() {
        items.clear();
    }

    public int finishPurchase() {
        final int totalInCents = calculateTotal();
        reset();
        return totalInCents;
    }

    public List<Product> allProducts() {
        return Collections.unmodifiableList(items);
    }
}
