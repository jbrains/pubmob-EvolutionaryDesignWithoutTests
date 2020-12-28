package com.pubmob.pos;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInProgress {
    private final List<Integer> prices;

    public PurchaseInProgress() {
        this.prices = new ArrayList<>();
    }

    public boolean addItemPrice(final int price) {
        return prices.add(price);
    }

    public int calculateTotal() {
        return prices.stream().reduce(0, Integer::sum);
    }

    public void reset() {
        prices.clear();
    }

    public int finishPurchase() {
        int total = calculateTotal();
        reset();
        return total;
    }
}
