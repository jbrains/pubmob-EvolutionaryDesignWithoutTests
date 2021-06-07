package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInProgress {
    private final List<Product> items;
    private boolean isPurchaseInProgress;

    public PurchaseInProgress() {
        this.items = new ArrayList<>();
        this.isPurchaseInProgress = false;
    }

    private int calculateTotal() {
        return items.stream().map(Product::cost).reduce(0, Integer::sum);
    }

    public boolean isPurchaseInProgress() {
        return isPurchaseInProgress;
    }

    public static class PurchaseInfo {
        public final int totalInCents;
        public final List<Product> items;

        public PurchaseInfo(final int totalInCents, final List<Product> items) {
            this.totalInCents = totalInCents;
            this.items = new ArrayList(items);
        }
    }

    public PurchaseInfo completePurchase() {
        final int totalInCents = calculateTotal();
        final PurchaseInfo purchaseInfo = new PurchaseInfo(totalInCents, items);
        isPurchaseInProgress = false;
        items.clear();
        return purchaseInfo;
    }

    // REFACTOR Introduce a Named Constructor begin() and then rename this to add().
    public void beginPurchaseWith(final Product item) {
        isPurchaseInProgress = true;
        items.add(item);
    }
}
