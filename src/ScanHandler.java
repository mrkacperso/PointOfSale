
/**
 * Created by Kacper on 29.05.2017.
 *
 * Actual ScanHandler to be implemented with real barcode scanner
 */
class ScanHandler {
    ProductDataStructure productDataStructure;

    ScanHandler(){}

    ScanHandler(String code) {
        handleScan(code);
    }

    private ScanHandler handleScan(String code) throws IllegalArgumentException {
            productDataStructure = getProductFromDatabase(new BarcodeParser(code).getBarcode());
            return this;
    }

    /**
     * Mocks the database query "SELECT * FROM Products WHERE Product.barcode='barcode';"
     * @param barcode barcode to search in database
     * @return null
     */
    private ProductDataStructure getProductFromDatabase(long barcode) {
        return null;
    }

    ProductDataStructure getProductDataStructure() {
        return productDataStructure;
    }
}
