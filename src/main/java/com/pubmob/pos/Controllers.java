package com.pubmob.pos;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controllers {
    private final PurchaseInProgress purchaseInProgress;
    private boolean canAskForAReceipt = false;

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

        if (canAskForAReceipt) {
            return Stream.of(itemsText, totalText)
                    .collect(Collectors.joining("\n"));
        } else {
            return "There is no completed purchase, so there is no receipt to print.";
        }
    }

    public String handleTotal(String ignored) {
        final int totalAmountInCents = completePurchase();

        return formatter.formatString(
                "Total: %s",
                monetaryAmountFormatter.formatMonetaryAmount(totalAmountInCents));
    }

    private int completePurchase() {
        canAskForAReceipt = true;
        final int totalAmountInCents = purchaseInProgress.finishPurchase();
        return totalAmountInCents;
    }

    public String handleBarcode(String barcode) {
        final Optional<Product> maybePriceForReal = productCatalog.findProduct(barcode);

        maybePriceForReal.ifPresent(item -> {
            beginPurchaseWith(item);
        });

        return maybePriceForReal
                .map(product -> formatter.formatString("%s", product.formatPrice()))
                .orElse(formatter.formatString("Barcode not found: %s.", barcode));
    }

    private void beginPurchaseWith(final Product item) {
        canAskForAReceipt = false;
        purchaseInProgress.addItem(item);
    }
}
