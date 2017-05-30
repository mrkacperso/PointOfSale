/**
 * Created by Kacper on 30.05.2017.
 *
 * ScanHandler for debug mode only
 */
class DebugScanHandler extends ScanHandler {

    private String name;
    private double price;

    DebugScanHandler(String code, String name, double price, boolean found){
        this.name = name;
        this.price = price;
        handleScan(code, found);
    }

    /**
     *
     * @param code Barcode from scanner
     * @param found Should simulate product found in database
     * @return ProductDataStructure from database, or null of no corresponding row was found
     * @throws IllegalArgumentException When barcode is incorrect
     */
    private ScanHandler handleScan(String code, boolean found) throws IllegalArgumentException {
        productDataStructure = getProductFromDatabase(new BarcodeParser(code).getBarcode(), found);
        return this;
    }

    /**
     * Mock database query
     * @param barcode barcode converted to long
     * @param found Should simulate product found in database
     * @return ProductDataStructure from database, or null of no corresponding row was found
     */
    private ProductDataStructure getProductFromDatabase(long barcode, boolean found) {
        if (found) {
            return new ProductDataStructure(barcode, name, price);
        } else return null;
    }
}
