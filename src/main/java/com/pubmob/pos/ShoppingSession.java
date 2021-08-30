package com.pubmob.pos;

public class ShoppingSession {
    private final PurchaseInProgress purchaseInProgress;

    public ShoppingSession() {
        this.purchaseInProgress = new PurchaseInProgress();
    }

    public boolean isPurchaseInProgress() {
        return purchaseInProgress.isPurchaseInProgress();
    }

    public void addProduct(final Product item) {
        purchaseInProgress.beginPurchaseWith(item);
    }

    public PurchaseInProgress.PurchaseInfo completePurchase() {
        final PurchaseInProgress.PurchaseInfo purchaseInfo = purchaseInProgress.summarizePurchase();
        purchaseInProgress.clear();
        return purchaseInfo;
    }
}
