package com.pubmob.pos;


import java.util.Optional;

public class Controllers {
    private final PurchaseInProgress shoppingCart;
    private final ProductCatalog productCatalog;

    public Controllers(ProductCatalog productCatalog, final PurchaseInProgress shoppingCart) {
        this.productCatalog = productCatalog;
        this.shoppingCart = shoppingCart;
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    public String handleTotal() {
        int total = shoppingCart.finishPurchase();
        return formatPrice(total);
    }

    public String handleBarcode(String barcode) {
        Optional<Integer> maybePrice = productCatalog.getPrice(barcode);

        maybePrice.ifPresent(price -> shoppingCart.addItemPrice(price));

        return maybePrice
                .map(Controllers::formatPrice)
                .orElse(String.format("Barcode not found: %s.", barcode));
    }

    // REFACTOR Remove me
    private Optional<Integer> getPrice(String barcode) {
        return productCatalog.getPrice(barcode);
    }
}
