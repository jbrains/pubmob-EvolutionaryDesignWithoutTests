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
        return TotalFormatter.formatTotal(purchaseInProgress.finishPurchase());
    }

    public String handleBarcode(String barcode) {
        final Optional<Product> maybePriceForReal = productCatalog.findProduct(barcode);

        maybePriceForReal.ifPresent(price -> purchaseInProgress.addItemPrice(price));

        return maybePriceForReal
                .map(Product::formatPrice)
                .orElse(String.format("Barcode not found: %s.", barcode));
    }
}
