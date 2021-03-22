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

        if (purchaseInProgress.canAskForAReceipt()) {
            return Stream.of(itemsText, totalText)
                    .collect(Collectors.joining("\n"));
        } else {
            return "There is no completed purchase, so there is no receipt to print.";
        }
    }

    public String handleTotal(String ignored) {
        final int totalAmountInCents = purchaseInProgress.completePurchase();

        return formatter.formatString(
                "Total: %s",
                monetaryAmountFormatter.formatMonetaryAmount(totalAmountInCents));
    }

    public String handleBarcode(String barcode) {
        final Optional<Product> maybePriceForReal = productCatalog.findProduct(barcode);

        // SMELL Why is this called "begin purchase with"?! Sometimes it's just "add". :(
        maybePriceForReal.ifPresent(purchaseInProgress::beginPurchaseWith);

        return maybePriceForReal
                .map(product -> formatter.formatString("%s", product.formatPrice()))
                .orElse(formatter.formatString("Barcode not found: %s.", barcode));
    }
}
