# Today's session plan

- use Vavr `Stream` library in order to turn `takeWhile... not` into `takeUntil`, which @jbrains finds easier to read.
- fix defect with message when the barcode is empty.
- split `ShoppingCart` into smaller classes: separate finding barcodes from responding to finding a barcode. `ProductCatalog` and `ShoppingCart`
- add optional sales tax called "GST" of 5%
- add optional sales tax called "PST" of 8%

# Inbox

These items are _not_ in priority order.

- separate formatting behavior from printing to stdout.
- wrap lookup table (barcode -> price) in domain class.
- introduce a value type for prices to replace `int`.
