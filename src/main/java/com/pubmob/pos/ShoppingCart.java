package com.pubmob.pos;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private final List<Integer> prices;
    private final ProductCatalog productCatalog;

    public ShoppingCart(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        this.prices = new ArrayList<>();
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    public String getTotal() {
        String formatPrice = formatPrice(prices.stream().reduce(0, Integer::sum));
        this.prices.clear();
        return formatPrice;
    }

    public String handleBarcode(String barcode) {
        Optional<Integer> maybePrice = productCatalog.getPrice(barcode);

        maybePrice.ifPresent(price -> prices.add(price));

        return maybePrice
                .map(ShoppingCart::formatPrice)
                .orElse(String.format("Barcode not found: %s.", barcode));
    }

    private Optional<Integer> getPrice(String barcode) {
        return productCatalog.getPrice(barcode);
    }
}
