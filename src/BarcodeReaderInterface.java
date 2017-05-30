/**
 * Created by Kacper on 28.05.2017.
 *
 * Barcode readers interface
 */
public interface BarcodeReaderInterface {

    /**
     * Interface used to pass barcode from scanner to application
     * @param code barcode read from scanner
     */
    void inputBarcode(String code);

    /**
     * FOR DEBIGGING ONLY
     * Interface used to pass read barcode from scanner to application
     * @param code barcode read from scanner
     */
    void inputBarcode(String code, String name, double price);

}
