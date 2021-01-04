package com.pubmob.pos;

import java.util.Map;
import java.util.Optional;

public class ProductCatalog {
    private final Map<String, Product> productsByBarcode;

    public ProductCatalog(final Map<String, Product> productsByBarcode) {
        this.productsByBarcode = productsByBarcode;
    }

    public Optional<Product> findProduct(final String barcode) {
        return Optional.ofNullable(productsByBarcode.get(barcode));
    }
}