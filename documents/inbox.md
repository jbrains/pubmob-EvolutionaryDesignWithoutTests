# Today's session plan

- Print a receipt
  - **DEFECT**. Somehow we can print a receipt before the first purchase starts.
  - Print the barcode with each item
    - Include the barcode when we add an item to the purchase in progress
      - Use Map.Entry for now, then introduce a PurchaseItem/CatalogItem class
  - Add a header with company identifying information (name, address, phone number, email...)
  - Add a timestamp for the receipt
  - Line up all the decimal points
  - Clean up before moving on:
    - **Stop reusing a single instance of `PurchaseInProgress` to represent the purchase in progress. Maintaining the state has already become too complicated!**

# Inbox

These items are _not_ in priority order.

- `MonetaryAmount` probably needs to be a Value Object.
- Extract sales tax as a first class concept from Product
- Report of completed purchases
  - Display in the application
  - Export to CSV
- (Re)print a receipt for a past purchase
- HST in PEI
