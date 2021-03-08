package com.pubmob.pos;

import java.util.Optional;

public class Controllers {
    private final PurchaseInProgress purchaseInProgress;
    private final ProductCatalog productCatalog;
    private final Formatter formatter = new Formatter();
    private final MonetaryAmountFormatter monetaryAmountFormatter = new MonetaryAmountFormatter(formatter);

    public Controllers(ProductCatalog productCatalog, final PurchaseInProgress purchaseInProgress) {
        this.productCatalog = productCatalog;
        this.purchaseInProgress = purchaseInProgress;
    }

    public String handleReceipt(final String ignored) {
        return "Coming soon!";
    }

    public String handleTotal(String ignored) {
        return formatter.formatString(
                "Total: %s",
                monetaryAmountFormatter.formatMonetaryAmount(purchaseInProgress.finishPurchase()));
    }

    public String handleBarcode(String barcode) {
        final Optional<Product> maybePriceForReal = productCatalog.findProduct(barcode);

        maybePriceForReal.ifPresent(purchaseInProgress::addItem);

        return maybePriceForReal
                .map(product -> formatter.formatString("%s", product.formatPrice()))
                .orElse(formatter.formatString("Barcode not found: %s.", barcode));
    }
}
