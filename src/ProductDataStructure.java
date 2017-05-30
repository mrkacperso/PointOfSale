/**
 * Created by Kacper on 28.05.2017.
 * Data structure for each product
 */
public class ProductDataStructure {

    private long barcode;
    private String productName;
    private double price;

    ProductDataStructure(long code, String productName, double price) {
        this.barcode = code;
        this.productName = productName;
        this.price = price;
    }

    public long getBarcode() {
        return this.barcode;
    }

    String getProductName() {
        return this.productName;
    }


    double getPrice() {
        return this.price;
    }

    String[] prepareDisplayData(String name, int quan, double pr) {
        String[] data = new String[3];
        data[0] = name;
        data[1] = String.valueOf(quan);
        data[2] = String.valueOf(pr);
        return data;
    }
}
