package w1956425;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// ShoppingCartGUI class representing the graphical user interface for the shopping cart
public class ShoppingCartGUI extends JFrame {

    //public static String product_id;//aditionally add
    private JTable cartTable;
    private ShoppingCart shoppingCart;
    private JLabel totalLabel;
    private JLabel totalDescriptionLabel;
    private JLabel discountLabel;
    private JLabel discount2Lable;
    private JLabel discountDescriptionLabel;
    private JLabel finalTotalLabel;
    private JLabel finalTotalDescriptionLabel;
    private JLabel discountDescription2Label;

    // Constructor to initialize the GUI with a specific shopping cart
    public ShoppingCartGUI(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;

        // Initializing GUI components
        cartTable = new JTable();
        updateCartTable();

        totalDescriptionLabel = new JLabel("Total");
        totalDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel = new JLabel("0.00£");
        totalLabel.setBorder(new EmptyBorder(0,30,0,0));
        discountDescription2Label= new JLabel("First purchase Discount (10%)");
        discountDescription2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        discount2Lable = new JLabel("- 0.00£");
        discount2Lable.setBorder(new EmptyBorder(0,30,0,0));
        discountDescriptionLabel = new JLabel("Three items in same Category Discount (20%)");
        discountDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        discountLabel = new JLabel("- 0.00£");
        discountLabel.setBorder(new EmptyBorder(0,30,0,0));
        finalTotalDescriptionLabel = new JLabel("Final Total");
        finalTotalDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        finalTotalLabel = new JLabel("0.00£");
        finalTotalLabel.setBorder(new EmptyBorder(0,30,0,0));


        updateFinalTotal();

        JPanel tablePanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBorder(new EmptyBorder(40,50,40,50));
        tablePanel.add(scrollPane, BorderLayout.CENTER);


        JPanel totalPanel = new JPanel(new GridLayout(4, 2));
        totalPanel.add(totalDescriptionLabel);
        totalPanel.add(totalLabel);
        totalPanel.add(discountDescription2Label);
        totalPanel.add(discount2Lable);
        totalPanel.add(discountDescriptionLabel);
        totalPanel.add(discountLabel);
        totalPanel.add(finalTotalDescriptionLabel);
        totalPanel.add(finalTotalLabel);

        /*
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(btnPanel);
        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSelectedProductId();
                shoppingCart.removeProduct(product_id);
                setVisible(false);
                setVisible(true);
            }
        });
        btnPanel.add(removeBtn);

         */

        totalPanel.setBorder(new EmptyBorder(10,10,10,10));
        tablePanel.add(totalPanel, BorderLayout.SOUTH);

        add(tablePanel);

        setSize(600, 600);
    }

    // Method to update the content of the cart table
    public void updateCartTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product");
        model.addColumn("Quantity");
        model.addColumn("Price");

        for (Map.Entry<Product,Integer> entry:shoppingCart.getProductQuantity().entrySet()){
            Product product = entry.getKey();
            int count = entry.getValue();
            model.addRow(new Object[]{getProductDetails(product),count,product.getProductPrice()});
        }

        cartTable.setModel(model);
    }

    // Method to format and retrieve details of a product for display
    private String getProductDetails(Product product) {
        if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            return product.getProductID()+'\n'+product.getProductName()+'\n'+ clothing.getClothingSize()+'\n'+ clothing.getClothingColour();
        } else if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            return product.getProductID()+'\n'+ product.getProductName()+'\n'+ electronics.getElectronicWarrantyPeriod();
        } else {
            return "Unknown Product Type";
        }
    }

    // Method to update the final total and discount labels
    private void updateFinalTotal(){
        double total = shoppingCart.calculateTotal();// = calculateTotal()
        double discount10 = shoppingCart.calculateDiscount10();
        double discount20 = shoppingCart.calculateDiscount20();
        double finalTotal = shoppingCart.calculateFinalTotal();

        totalLabel.setText(String.format("%.2f£", total));
        discount2Lable.setText(String.format("- %.2f£", discount10));
        discountLabel.setText(String.format("- %.2f£", discount20));
        finalTotalLabel.setText(String.format("%.2f£", finalTotal));
    }

    /*
    public String getSelectedProductId() {
        int column = 0;
        int row = cartTable.getSelectedRow();
        String productDetails = cartTable.getModel().getValueAt(row, column).toString();
        String[] lines = productDetails.split("\n");
        if (lines.length > 0) {
            product_id = lines[0];
        }
        return product_id;
    }
    */
}
