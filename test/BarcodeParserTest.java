import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kacper on 28.05.2017.
 */
public class BarcodeParserTest {

    BarcodeParser barcodeParser;
    String code = "1234567891234";
    long result = 1234567891234L;

    @Before
    public void setUp() throws Exception {
        barcodeParser = new BarcodeParser();
    }

    @Test
    public void setBarcode() throws Exception {
        assertEquals(result,barcodeParser.setBarcode(code));
    }

}