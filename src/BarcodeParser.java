/**
 * Created by Kacper on 28.05.2017.
 *
 * Parsing and validating barcode
 */
class BarcodeParser {

    private long barcode;

    BarcodeParser(String code){
        this.barcode = setBarcode(code);
    }

    BarcodeParser(){
    }

    /**
     *
     * @param code Barcode red from scanner in form of String
     * @return Barcode converted to long (13 digits). If code is shorter, or contains character(s)
     */
    long setBarcode(String code) throws IllegalArgumentException{
        long codeValue;
        try {
            codeValue = Long.parseLong(code);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("This is not EAN-13 barcode");
        }
        if (testBarcode(codeValue))return codeValue;
        else throw new IllegalArgumentException("This is not EAN-13 barcode");
    }

    /**
     * Check if barcode is 13 digits long
     * @param code testes code
     * @return true if this code is 13 digits long, otherwise false
     */
    private boolean testBarcode(long code){
        return String.valueOf(code).length() == 13;
    }

    long getBarcode(){
        return this.barcode;
    }

}
