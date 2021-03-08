package com.pubmob.pos;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String itemsText = purchaseInProgress.allProducts()
                .stream()
                .map(Product::formatPrice)
                .collect(Collectors.joining("\n"));

        String totalText = handleTotal(ignored);

        return Stream.of(itemsText, totalText)
                .collect(Collectors.joining("\n"));
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
