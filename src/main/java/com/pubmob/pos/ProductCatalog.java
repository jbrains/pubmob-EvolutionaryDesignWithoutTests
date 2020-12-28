package com.pubmob.pos;

import java.util.Map;
import java.util.Optional;

public class ProductCatalog {
    private final Map<String, Price> pricesByBarcode;

    public ProductCatalog(final Map<String, Price> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    public Optional<Price> findPrice(final String barcode) {
        return Optional.ofNullable(pricesByBarcode.get(barcode));
    }
}