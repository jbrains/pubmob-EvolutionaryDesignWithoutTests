
package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInProgress {
    public final List<Product> items;
    public boolean isPurchaseInProgress;

    public PurchaseInProgress() {
        this.items = new ArrayList<>();
        this.isPurchaseInProgress = false;
        purchaseInProgressIsConsistent();
    }

    public int calculateTotal() {
        return items.stream().map(Product::cost).reduce(0, Integer::sum);
    }

    public boolean isPurchaseInProgress() {
        return isPurchaseInProgress;
    }

    private void purchaseInProgressIsConsistent() {
        boolean statesMatch = this.items.isEmpty() != this.isPurchaseInProgress;
        if (!statesMatch)
            throw new RuntimeException("BLEH!");
    }
    public PurchaseInfo calculateTotalThenWrapInPurchaseInfo() {
        // SMELL We calculate the total from the items, then we pass _both_ into the PurchaseInfo constructor.
        final int totalInCents = calculateTotal();
        final PurchaseInfo purchaseInfo = new PurchaseInfo(totalInCents, items);
        return purchaseInfo;
    }

    public void clear() {
        isPurchaseInProgress = false;
        items.clear();
        purchaseInProgressIsConsistent();
    }

    public static class PurchaseInfo {
        public final int totalInCents;
        public final List<Product> items;

        public PurchaseInfo(final int totalInCents, final List<Product> items) {
            this.totalInCents = totalInCents;
            this.items = new ArrayList(items);
        }
    }

    // REFACTOR Introduce a Named Constructor begin() and then rename this to add().
    public void beginPurchaseWith(final Product item) {
        isPurchaseInProgress = true;
        items.add(item);
        purchaseInProgressIsConsistent();
    }
}
