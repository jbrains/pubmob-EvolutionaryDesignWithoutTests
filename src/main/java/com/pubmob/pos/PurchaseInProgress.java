package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInProgress {
    private final List<Integer> prices;

    public PurchaseInProgress() {
        this.prices = new ArrayList<>();
    }

    public boolean addItemPrice(Price price) {
        return prices.add(price.inCents());
    }

    private int calculateTotal() {
        return prices.stream().reduce(0, Integer::sum);
    }

    private void reset() {
        prices.clear();
    }

    public Price finishPurchase() {
        final Price total = new Price(calculateTotal());
        reset();
        return total;
    }
}
