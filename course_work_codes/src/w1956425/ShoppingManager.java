package w1956425;

public interface ShoppingManager {
    //Add a product to the system
    void addProductToSystem(Product product);

    //Print the sorted list of products to the console
    void printSystemProductList();

    //Save the system product list to a file.
    void saveInFile();

    //Load product data from a file into the system
    void loadFromFile();

}
