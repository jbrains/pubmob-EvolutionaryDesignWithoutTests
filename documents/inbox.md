# Inbox

- use `final` more consistently (IDEA setting?)
- change the "barcode not found" message so that Gradle rich console doesn't parse it: don't let it start with `error:`.
- reset the "Sale" after the `total` command, so that the next shopper can buy things.
- check that the `total` command works, even before the shopper has scanned a barcode.
- rename `BarcodeInventory.`
- split `BarcodeInventory` into smaller classes: separate finding barcodes from responding to finding a barcode.
- introduce abstractions for System.out? print action?
- wrap lookup table in domain class.
- introduce a value type for prices to replace `int`.

