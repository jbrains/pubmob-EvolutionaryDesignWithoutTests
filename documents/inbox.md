# Today's session plan

- Clean up before moving on:
  - Make `PurchaseInfo` and `PurchaseInProgress` more similar; maybe collapse them into one thing?
    - Move `PurchaseInfo.getTotalInCents()` onto `PurchaseInProgress`.
    - Find clients of `PurchaseInfo`, then make them use the corresponding `PurchaseInProgress` instead. This obviates `PurchaseInfo`.
    - Rename `PurchaseInProgress` to `Purchase`.
  - **Stop reusing a single instance of `PurchaseInProgress` to represent the purchase in progress. Maintaining the state has already become too complicated!**
    - The class and one of its properties have the same name. Improve this _somehow_.
    - Make the lifecycle of the Purchase in Progress more obvious in the API. For example: Purchase.begin() returns PurchaseInProgress; purchaseInProgress.complete() returns PurchaseInfo.
- Print a receipt
  - What happens if we have a total with no items? scenario
  - Print the barcode with each item in the receipt
    - Include the barcode when we add an item to the purchase in progress
      - Use Map.Entry for now, then introduce a PurchaseItem/CatalogItem class
  - Add a header with company identifying information (name, address, phone number, email...)
  - Add a timestamp for the receipt
  - Line up all the decimal points
  - Defect: excessive whitespace when requesting a receipt for an empty Purchase.
  - Introduce a View Model class for Receipt that handles formatting instead of `Controllers`.

# Inbox

These items are _not_ in priority order.

- `MonetaryAmount` probably needs to be a Value Object.
- Extract sales tax as a first class concept from Product
- Report of completed purchases
  - Display in the application
  - Export to CSV
- (Re)print a receipt for a past purchase
- HST in PEI
- "split bill" scenario  --> 2 orders and pay separately

# Notes
General intention: Print recent purchase
