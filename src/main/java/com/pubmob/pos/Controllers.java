package com.pubmob.pos;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controllers {
    private final PurchaseInProgress purchaseInProgress;
    private final ProductCatalog productCatalog;
    private final Formatter formatter = new Formatter();
    private final MonetaryAmountFormatter monetaryAmountFormatter = new MonetaryAmountFormatter(formatter);
    private PurchaseInProgress.PurchaseInfo recentlyCompletedPurchase;

    public Controllers(ProductCatalog productCatalog, final PurchaseInProgress purchaseInProgress) {
        this.productCatalog = productCatalog;
        this.purchaseInProgress = purchaseInProgress;
    }

    public String handleReceipt(final String ignored) {
        if (recentlyCompletedPurchase == null) {
            return "There is no recently completed purchase for which to print a receipt.";
        }
        if (!purchaseInProgress.canAskForAReceipt()) {
            return "There is no completed purchase, so there is no receipt to print.";
        }

        String itemsText = formatItems();
        String totalText = formatTotal(recentlyCompletedPurchase.totalInCents);

        return Stream.of(itemsText, totalText)
                .collect(Collectors.joining("\n"));
    }

    private String formatItems() {
        String itemsText = recentlyCompletedPurchase.items
                .stream()
                .map(Product::formatPrice)
                .collect(Collectors.joining("\n"));
        return itemsText;
    }

    public String handleTotal(String ignored) {
        recentlyCompletedPurchase = purchaseInProgress.completePurchase();
        final int totalAmountInCents = recentlyCompletedPurchase.totalInCents;

        return formatTotal(totalAmountInCents);
    }

    private String formatTotal(final int totalAmountInCents) {
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
