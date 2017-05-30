import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Kacper on 26.05.2017.
 * Main logic application class
 */
public class CoreAppLogic extends JFrame implements ActionListener, BarcodeReaderInterface, ProductInsertionListener{

    private JButton exitButton,
            nextCustomerButton;
    private List<JLabel> detailsLabels;
    private ProductList productList;
    private DefaultTableModel tableModel;

    private boolean debug = true;
    private JTextField debugTextFieldBarcode,
            debugTextFieldPrice,
            debugTextFieldName;
    private JCheckBox debugCheckFoundInDatabase;
    private JButton debugScanButton;


    CoreAppLogic(String text) {
        super(text);
        productList = new ProductList(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);

        setLayout(new GridLayout(1, 2));
        initializeGUI();
    }

    private void initializeGUI() {
        //Main panels
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.setBackground(Color.white);

        JPanel rightPane = new JPanel(new GridLayout(2, 0));

        //  Left panel elements
        //Left panel product list
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Price");

        JTable productListPane = new JTable();
        productListPane.setModel(tableModel);
        productListPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20 ,0));
        JScrollPane productListContainer = new JScrollPane(productListPane);
        leftPane.add(productListContainer, BorderLayout.PAGE_START);

        //Left panel product details
        //Left panel product description text fields
        detailsLabels = new ArrayList<>();
        JPanel productDetailPane = new JPanel(new GridLayout(1, 3));
        createDetailLabels(productDetailPane, detailsLabels);

        //Scroll Tab
        leftPane.add(productDetailPane, BorderLayout.CENTER);

        // -- End of left panel elements

        //Right panel elements
        JPanel buttonsPane = new JPanel(new GridLayout(3, 0));

        //Right panel buttons
        //TODO DEBUG ONLY
        JPanel debugPanel = new JPanel(new GridLayout(2, 1));
        JPanel debugPanelUp = new JPanel(new GridLayout(1, 3));
        JPanel debugPanelDown = new JPanel(new BorderLayout());

        JPanel debugPanel1 = new JPanel(new GridLayout(2, 1));
        JPanel debugPanel2 = new JPanel(new GridLayout(2, 1));
        JPanel debugPanel3 = new JPanel(new GridLayout(2, 1));
        JPanel debugPanel4 = new JPanel(new GridLayout(2, 1));


        debugScanButton = new JButton("SCAN");
        debugScanButton.addActionListener(this);

        if (debug) {
            debugTextFieldBarcode = new JTextField();
            debugCheckFoundInDatabase = new JCheckBox();
            debugTextFieldPrice = new JTextField();
            debugTextFieldName = new JTextField();

            debugTextFieldBarcode = new JTextField();
            JLabel debugBarcodeDescription = new JLabel();
            debugBarcodeDescription.setText("Simulate barcode");
            debugPanel1.add(debugBarcodeDescription);
            debugPanel1.add(debugTextFieldBarcode);
            debugPanelUp.add(debugPanel1);

            debugCheckFoundInDatabase = new JCheckBox();
            JLabel debugDatabaseDescription = new JLabel();
            debugDatabaseDescription.setText("Check to find in DB");
            debugPanel2.add(debugDatabaseDescription);
            debugPanel2.add(debugCheckFoundInDatabase);
            debugPanelUp.add(debugPanel2);

            debugTextFieldPrice = new JTextField();
            JLabel debugPriceDescription = new JLabel();
            debugPriceDescription.setText("Simulate price in db");
            debugPanel3.add(debugPriceDescription);
            debugPanel3.add(debugTextFieldPrice);
            debugPanelUp.add(debugPanel3);

            debugTextFieldName = new JTextField();
            JLabel debugNameDescription = new JLabel();
            debugNameDescription.setText("Simulate product name in db");
            debugPanel4.add(debugNameDescription);
            debugPanel4.add(debugTextFieldName);
            debugPanelDown.add(debugPanel4);

            debugPanel.add(debugPanelUp);
            debugPanel.add(debugPanelDown);
        }

        // END OF DEBUG

        //Buttons holder
        JPanel buttonsHolderPanel = new JPanel(new GridLayout(1, 2));

        //Exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        //Next customer button
        nextCustomerButton = new JButton("Next customer");
        nextCustomerButton.addActionListener(this);
        nextCustomerButton.setVisible(false);

        buttonsHolderPanel.add(nextCustomerButton);
        buttonsHolderPanel.add(exitButton);
        buttonsPane.add(buttonsHolderPanel);
        if (debug) {
            buttonsPane.add(debugPanel);
            buttonsPane.add(debugScanButton);
        }
        rightPane.add(buttonsPane);

        // -- End of right panel elements

        this.add(leftPane);
        this.add(rightPane);
    }

    /**
     * Create labels displaying details about product on the bottom of the screen
     * @param container Container
     * @param labelsList List containing labels
     */
    private void createDetailLabels(JPanel container, List<JLabel> labelsList) {

        for (int i = 0; i< 2; i++){
            JLabel tmpLabel = new JLabel();
            tmpLabel.setHorizontalAlignment(JLabel.CENTER);
            tmpLabel.setFont(new Font("Arial", Font.BOLD, 14));
            labelsList.add(tmpLabel);
            container.add(tmpLabel);
        }
    }

    /**
     * Set text on labels displaying details about product on the bottom of the screen
     * @param data Data to be displayed data[0] - product name, data[1[ - product price
     * @param labels List containing labels
     */
    private void setDescriptionText(String[] data, List<JLabel> labels) {
        for (int i=0; i<labels.size(); i++) {
            labels.get(i).setText(data[i]);
        }
    }

    /**
     * Displays popup window with given title, and description
     * @param title Window title
     * @param description Window description
     */
    private void showPopup(String title, String description){
        JOptionPane.showMessageDialog(this, description, title, JOptionPane.ERROR_MESSAGE);
    }

    /*
     * Buttons actionListener consumer
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exitButton) {
            String data[] = new String[2];
            data[0] = "Total: ";
            data[1] = String.valueOf(productList.getTotalSum());
            setDescriptionText(data, detailsLabels);
            debugScanButton.setVisible(false);
            nextCustomerButton.setVisible(true);
            System.out.println(productList.toString());
        }
        else if (source == nextCustomerButton) {
            debugScanButton.setVisible(true);
            nextCustomerButton.setVisible(false);
            for (int i=0; i<productList.getSize(); i++){
                tableModel.removeRow(0);
            }
            productList.clear();
        }

        if (debug) {
            if (source == debugScanButton) {
                double price;
                try {
                    price= Double.parseDouble(debugTextFieldPrice.getText());
                    BarcodeReaderInterface barcodeReaderInterface = this;
                    barcodeReaderInterface.inputBarcode(debugTextFieldBarcode.getText(), debugTextFieldName.getText(), price);
                } catch (IllegalArgumentException e1) {
                    showPopup("Debug data is wrong", "Please provide price in format 12.34");
                }
            }
        }
    }

    /*
     * Callback triggered when barcode is scanned
     */
    @Override
    public void inputBarcode(String code) {
        ScanHandler scanHandler =new ScanHandler(code);
        productList.add(scanHandler.getProductDataStructure());
    }


    /**
     * Debug inputBarcode method
     * @param code barcode read from scanner
     * @param price DEBUG price
     */
    @Override
    public void inputBarcode(String code, String name, double price) {
        try {
            DebugScanHandler debugScanHandler = new DebugScanHandler(code, name, price,debugCheckFoundInDatabase.isSelected());
            if (debugScanHandler.getProductDataStructure() != null) {
                productList.add(debugScanHandler.getProductDataStructure());
                //System.out.println(productList);
            } else showPopup("Not found", "Product not found in database");
        } catch (IllegalArgumentException e) {
            showPopup("Barcode error", "This is not an EAN-13 barcode");
        }
    }

    /*
     * Callback triggered when product is inserted to products list
     */
    @Override
    public void productInserted(ProductDataStructure productDataStructure) {
        String data[] = new String[2];
        data[0] = productDataStructure.getProductName();
        data[1] = String.valueOf(productDataStructure.getPrice());
        tableModel.addRow(data);
        setDescriptionText(data, detailsLabels);
    }
}
