# Inbox

- split `ShoppingCart` into smaller classes: separate finding barcodes from responding to finding a barcode. `ProductCatalog` and `ShoppingCart`
- introduce abstractions for System.out? print action? - premature, but may revisit later (maybe call it a view? formatter of some sort? where it goes?)
- wrap lookup table in domain class.
- introduce a value type for prices to replace `int`.
- use Vavr `Stream` library in order to turn `takeWhile... not` into `takeUntil`, which @jbrains finds easier to read.

