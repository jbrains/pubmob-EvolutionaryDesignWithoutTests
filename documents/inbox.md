# Inbox

- rename `BarcodeInventory`.
- introduce abstraction for barcode lookup and pull out of main class 
- introduce abstractions for System.out? print action? - premature, but may revisit later (maybe call it a view? formatter of some sort? where it goes?)
- wrap lookup table in domain class (aka finding the price of a barcode)
- introduce a value type for prices to replace `int`.
- use Vavr `Stream` library in order to turn `takeWhile... not` into `takeUntil`, which @jbrains finds easier to read.
- who gets the responsibility to reset the basket? Should be outside of getTotal(). and maybe use "new ShoppingCart()"
- externalize user facing strings/text