package com.pubmob.pos;

import java.util.Optional;

public class Controllers {
    private final PurchaseInProgress purchaseInProgress;
    private final ProductCatalog productCatalog;
    private final Formatter formatter = new Formatter();

    public Controllers(ProductCatalog productCatalog, final PurchaseInProgress purchaseInProgress) {
        this.productCatalog = productCatalog;
        this.purchaseInProgress = purchaseInProgress;
    }

    public String handleTotal(String ignored) {
        final String template = "Total: %s";
        final String parameter = MonetaryAmountFormatter.formatMonetaryAmount(purchaseInProgress.finishPurchase());
        return formatter.formatString(template, parameter);
    }

    public String handleBarcode(String barcode) {
        final Optional<Product> maybePriceForReal = productCatalog.findProduct(barcode);

        maybePriceForReal.ifPresent(purchaseInProgress::addItem);

        final String template = "Barcode not found: %s.";
        return maybePriceForReal
                .map(product -> formatter.formatString("%s", product.formatPrice()))
                .orElse(formatter.formatString(template, barcode));
    }
}
