package com.pubmob.pos;


import java.util.Optional;

public class Controllers {
    private final PurchaseInProgress purchaseInProgress;
    private final ProductCatalog productCatalog;

    public Controllers(ProductCatalog productCatalog, final PurchaseInProgress purchaseInProgress) {
        this.productCatalog = productCatalog;
        this.purchaseInProgress = purchaseInProgress;
    }

    private static String formatPrice(int cents) {
        return String.format("$%.2f", cents / 100d);
    }

    public String handleTotal() {
        int total = purchaseInProgress.finishPurchase();
        return formatPrice(total);
    }

    public String handleBarcode(String barcode) {
        Optional<Integer> maybePrice = productCatalog.getPrice(barcode);

        maybePrice.ifPresent(price -> purchaseInProgress.addItemPrice(price));

        return maybePrice
                .map(Controllers::formatPrice)
                .orElse(String.format("Barcode not found: %s.", barcode));
    }
}
