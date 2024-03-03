package w1956425;

// Clothing class, extending the Product class
public class Clothing extends Product{

    // Instance variables to store clothing size and color
    protected String clothing_size;
    protected String clothing_colour;

    // Constructor for the Clothing class
    public Clothing(String product_id, String product_name, int number_of_available_items, double price, String clothing_size, String clothing_colour) {
        // Call the constructor of the superclass (Product) using 'super' keyword
        super(product_id, product_name, number_of_available_items, price);

        // Initialize the specific attributes of the Clothing class
        this.clothing_size = clothing_size;
        this.clothing_colour = clothing_colour;
    }

    // Getter method for retrieving clothing size
    public String getClothingSize() {
        return this.clothing_size;
    }

    // Setter method for updating clothing size
    public void setClothingSize(String clothing_size) {
        this.clothing_size = clothing_size;
    }

    // Getter method for retrieving clothing color
    public String getClothingColour() {
        return this.clothing_colour;
    }

    // Setter method for updating clothing color
    public void setClothingColour(String clothing_colour) {
        this.clothing_colour = clothing_colour;
    }

    // Override the printInfo method from the superclass (Product)
    @Override
    public String printInfo() {
        return
                product_id + ','+
                product_name + ',' +
                number_of_available_items + ','+
                price + ','+
                clothing_colour + ',' +
                clothing_size + ','+
                "Clothing"
                ;
    }

    // Override the productType method from the superclass (Product)
    @Override
    public String productType() {
        return "Clothing";
    }

    // Override the info method from the superclass (Product)
    @Override
    public String info() {
        // Return a string containing clothing size and color
        return clothing_size+","+ clothing_colour;
    }

    // Override the infoGUI method from the superclass (Product)
    @Override
    public String infoGUI(){
        // Return a formatted string containing information for GUI display
        return "Product ID: " + product_id+ "\n" +
                "Category: Clothing"+ "\n"+
                "Name: "+ product_name+ "\n" +
                "Size: "+ clothing_size + "\n"+
                "Colour: "+ clothing_colour + "\n"+
                "Items Available: "+ number_of_available_items;
    }
}

