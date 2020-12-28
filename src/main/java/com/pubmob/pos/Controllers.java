package com.pubmob.pos;


import java.util.Optional;

public class Controllers {
    private final PurchaseInProgress purchaseInProgress;
    private final ProductCatalog productCatalog;

    public Controllers(ProductCatalog productCatalog, final PurchaseInProgress purchaseInProgress) {
        this.productCatalog = productCatalog;
        this.purchaseInProgress = purchaseInProgress;
    }

    public String handleTotal() {
        final Price totalAsPrice = purchaseInProgress.finishPurchase();
        return totalAsPrice.formatPrice();
    }

    public String handleBarcode(String barcode) {
        final Optional<Price> maybePriceForReal = productCatalog.getPrice(barcode);

        maybePriceForReal.ifPresent(price -> purchaseInProgress.addItemPrice(price));

        return maybePriceForReal
                .map(Price::formatPrice)
                .orElse(String.format("Barcode not found: %s.", barcode));
    }
}
