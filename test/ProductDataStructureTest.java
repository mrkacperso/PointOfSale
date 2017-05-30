import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kacper on 28.05.2017.
 */
public class ProductDataStructureTest {
    private ProductDataStructure productDataStructure;
    long code = 1234567890123L;
    String name;
    Double price;
    int quantity;
    String[] result;

    @Before
    public void setUp() throws Exception {
        name = "Tomatoes";
        price = 24.90;
        quantity = 4;
        result = new String[3];
        result[0] = name;
        result[1] = String.valueOf(price);
        result[2] = String.valueOf(quantity);
        productDataStructure = new ProductDataStructure(code, name, price);
    }

    @Test
    public void prepareDisplayData() throws Exception {
        assertArrayEquals(result, productDataStructure.prepareDisplayData(name, quantity, price));
    }

}