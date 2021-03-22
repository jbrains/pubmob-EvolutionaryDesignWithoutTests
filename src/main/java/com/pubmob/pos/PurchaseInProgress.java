package com.pubmob.pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchaseInProgress {
    private final List<Product> items;
    private boolean canAskForAReceipt = false;

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

    public List<Product> allProducts() {
        return Collections.unmodifiableList(items);
    }

    public int completePurchase() {
        final int totalInCents = calculateTotal();
        canAskForAReceipt = true;
        reset();
        return totalInCents;
    }

    // SMELL Shouldn't this be in the constructor?
    public void beginPurchaseWith(final Product item) {
        canAskForAReceipt = false;
        addItem(item);
    }

    public boolean canAskForAReceipt() {
        return canAskForAReceipt;
    }
}
