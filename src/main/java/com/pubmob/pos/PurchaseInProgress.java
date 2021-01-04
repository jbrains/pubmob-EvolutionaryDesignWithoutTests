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

    // SMELL We're using Product here to mean "Monetary Amount"
    // so that we can reuse the formatting.
    public Product finishPurchase() {
        // SMELL Make sure the "tax applies" is false, so that we don't see "G"
        final Product total = new Product(calculateTotal(), false);
        reset();
        return total;
    }
}
