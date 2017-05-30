import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper on 27.05.2017.
 *
 * List to store ProductDataStructure for each customer
 */
public class ProductList {

    private ProductInsertionListener productInsertionListener;

    private List<ProductDataStructure> productsList;

    ProductList(ProductInsertionListener listener) {
        this.productInsertionListener = listener;
        productsList = new ArrayList<>();
    }

    void add(ProductDataStructure product){
        productInsertionListener.productInserted(product);
        productsList.add(product);
    }

    double getTotalSum(){
        double sum = 0.00;
        for (ProductDataStructure product : productsList) {
            sum+=product.getPrice();
        }
        return sum;
    }

    int getSize() {
        return productsList.size();
    }

    void clear() {
        productsList.clear();
    }

    @Override
    public String toString() {
        String receipt = "Receipt: \n";

        for (ProductDataStructure product : productsList) {
            receipt+=product.getProductName()+"\t"+product.getPrice()+"\n";
        }
        receipt+="\n\n"+getTotalSum();

        return receipt;
    }

}
