package com.pubmob.pos;

import java.util.Map;
import java.util.Optional;

public class ProductCatalog {
    final Map<String, Integer> inventory;

    public ProductCatalog(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    Optional<Integer> getPrice(String barcode) {
        return Optional.ofNullable(inventory.get(barcode));
    }
}