package w1956425;

// Electronics class, extending the Product class
public class Electronics extends Product{

    // Instance variables to store electronic brand and warranty period
    protected String electronic_brand;
    protected int electronic_warrantyPeriod;

    // Constructor for the Electronics class
    public Electronics(String product_id, String product_name, int number_of_available_items, double price, String electronic_brand, int electronic_warrantyPeriod) {
        // Call the constructor of the superclass (Product) using 'super' keyword
        super(product_id, product_name, number_of_available_items, price);

        // Initialize the specific attributes of the Electronics class
        this.electronic_brand = electronic_brand;
        this.electronic_warrantyPeriod = electronic_warrantyPeriod;
    }

    // Getter method for retrieving electronic brand
    public String getElectronicBrand() {
        return this.electronic_brand;
    }

    // Setter method for updating electronic brand
    public void setElectronicBrand(String electronic_brand) {
        this.electronic_brand = electronic_brand;
    }

    // Getter method for retrieving electronic warranty period
    public int getElectronicWarrantyPeriod() {
        return this.electronic_warrantyPeriod;
    }

    // Setter method for updating electronic warranty period
    public void setElectronicWarrantyPeriod(int electronic_warrantyPeriod) {
        this.electronic_warrantyPeriod = electronic_warrantyPeriod;
    }

    // Override the productType method from the superclass (Product)
    @Override
    public String productType(){
        return "Electronics";
    }

    // Override the printInfo method from the superclass (Product)
    @Override
    public String printInfo() {
        return
                product_id + ',' +
                product_name + ',' +
                number_of_available_items + ','+
                price + ','+
                electronic_warrantyPeriod + ','+
                electronic_brand + ',' +
                "Electronic"
                ;

    }

    // Override the info method from the superclass (Product)
    @Override
    public String info() {
        // Return a string containing electronic brand and warranty period
        return electronic_brand+","+electronic_warrantyPeriod;
    }

    // Override the infoGUI method from the superclass (Product)
    @Override
    public String infoGUI() {
        // Return a formatted string containing information for GUI display
        return "Product ID: " + product_id+ "\n" +
                "Category: Electronics"+ "\n"+
                "Name: "+ product_name+ "\n" +
                "Brand: "+ electronic_brand + "\n"+
                "Warranty Period: "+ electronic_warrantyPeriod + "\n"+
                "Items Available: "+ number_of_available_items;
    }
}



