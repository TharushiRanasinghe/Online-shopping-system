package w1956425;

// Abstract class representing a  product
public abstract class Product {

    // Instance variables to store product information
    protected String product_id;
    protected String product_name;
    protected int number_of_available_items;
    protected double price;

    // Constructor for initializing common product attributes
    public Product(String product_id, String product_name, int number_of_available_items, double price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.number_of_available_items = number_of_available_items;
        this.price = price;
    }

    // Getter method for retrieving product ID
    public String getProductID() {
        return this.product_id;
    }

    // Setter method for updating product ID
    public void setProductID(String product_id) {
        this.product_id = product_id;
    }

    // Getter method for retrieving product name
    public String getProductName() {
        return this.product_name;
    }

    // Setter method for updating product name
    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    // Getter method for retrieving the number of available items
    public int getNumberOfAvailableItems() {
        return this.number_of_available_items;
    }

    // Setter method for updating the number of available items
    public void setNumberOfAvailableItems(int number_of_available_items) {
        this.number_of_available_items = number_of_available_items;
    }

    // Getter method for retrieving product price
    public double getProductPrice() {
        return price;
    }

    // Setter method for updating product price
    public void setProductPrice(double price) {
        this.price = price;
    }

    // Abstract method to print product information
    public abstract String printInfo();

    // Abstract method to get the type of the product
    public abstract String productType();

    // Abstract method to retrieve additional information specific to the product
    public abstract String info();

    // Abstract method to provide information suitable for GUI display
    public abstract String infoGUI();
}




