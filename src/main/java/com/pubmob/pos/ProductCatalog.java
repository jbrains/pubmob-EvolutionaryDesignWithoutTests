package com.pubmob.pos;

import java.util.Map;
import java.util.Optional;

public class ProductCatalog {
    private final Map<String, Price> productCodePriceMap;

    public ProductCatalog(final Map<String, Price> productCodePriceMap) {
        this.productCodePriceMap = productCodePriceMap;
    }

    public Optional<Price> getPrice(final String barcode) {
        return Optional.ofNullable(productCodePriceMap.get(barcode));
    }
}