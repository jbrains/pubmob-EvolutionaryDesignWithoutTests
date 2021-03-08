# Today's session plan

- Print a receipt
  - Include the barcode when we add an item to the purchase in progress
    - Use Map.Entry for now, then introduce a PurchaseItem/CatalogItem class
  - Decide when is it OK for the cashier to ask for the receipt?
    - Only at the end of the purchase? or during the purchase?
  - Add a header with company identifying information (name, address, phone number, email...)
  - Line up all the decimal points
  - Print the barcode with each item
  - Add a timestamp for the receipt (how urgent is this?)

# Inbox

These items are _not_ in priority order.

- `MonetaryAmount` probably needs to be a Value Object.
- Extract sales tax as a first class concept from Product
- Report of completed purchases
  - Display in the application
  - Export to CSV
- (Re)print a receipt for a past purchase
- HST in PEI
