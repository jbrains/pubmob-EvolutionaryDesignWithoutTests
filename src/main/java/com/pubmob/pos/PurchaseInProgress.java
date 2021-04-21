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

    private int calculateTotal() {
        return items.stream().map(Product::cost).reduce(0, Integer::sum);
    }

    public List<Product> allProducts() {
        return Collections.unmodifiableList(items);
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
        canAskForAReceipt = true;
        items.clear();
        return purchaseInfo;
    }

    // SMELL Shouldn't this be in the constructor?
    public void beginPurchaseWith(final Product item) {
        canAskForAReceipt = false;
        items.add(item);
    }

    public boolean canAskForAReceipt() {
        return canAskForAReceipt;
    }
}
