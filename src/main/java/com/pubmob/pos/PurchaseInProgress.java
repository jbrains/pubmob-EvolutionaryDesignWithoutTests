
package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInProgress {
    private final List<Product> items;

    public PurchaseInProgress() {
        this.items = new ArrayList<>();
    }


    public boolean isPurchaseInProgress() {
        return !this.items.isEmpty();
    }

    public PurchaseInfo summarizePurchase() {
        return new PurchaseInfo(items);
    }

    public void clear() {
        items.clear();
    }

    public static class PurchaseInfo {
        public final List<Product> items;

        public PurchaseInfo(final List<Product> items) {
            this.items = new ArrayList(items);
        }

        public int getTotalInCents() {
            return items.stream().map(Product::cost).reduce(0, Integer::sum);
        }
    }

    // REFACTOR Introduce a Named Constructor begin() and then rename this to add().
    public void beginPurchaseWith(final Product item) {
        items.add(item);
    }
}
