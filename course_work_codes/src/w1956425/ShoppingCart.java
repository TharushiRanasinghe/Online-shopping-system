package w1956425;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    //public ShoppingCartGUI gui; //extra added

    // List to store products in the cart
    private static List<Product> cartProductList;

    // Map to store product and its quantity in the cart
    private Map<Product,Integer> productQuantity;

    // User who owns the shopping cart
    private User owner;

    // Constructor to initialize a shopping cart for a specific user
    public ShoppingCart(User owner) {
        this.cartProductList = new ArrayList<>();
        this.productQuantity = new HashMap<>();
        this.owner= owner;
    }

    // Method to add a product to the shopping cart
    public void addProductToCart(Product product){
        int count = productQuantity.getOrDefault(product,0);
        productQuantity.put(product,count+1);
        this.cartProductList.add(product);

    }
    // Method to retrieve the list of products in the cart
    public List<Product> getProducts() {
        return cartProductList;
    }

    // Method to retrieve the quantity of each product in the cart
    public Map<Product,Integer> getProductQuantity(){
        return productQuantity;
    }

    // Method to check if the cart is eligible for a 20% discount based on product categories
    public boolean discountEligible() {
        Map<String, Integer> categoryCount = new HashMap<>();

        for (Product product : cartProductList) {
            String category = getCategoryFromProduct(product);
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }

        // Check if the user has bought three or more products from the same category
        for (int count : categoryCount.values()) {
            if (count >= 3) {
                return true; // Eligible for a 20% discount
            }
        }

        return false; // Not eligible for a discount
    }

    // Method to get the category of a product (Electronics, Clothing, Unknown)
    private String getCategoryFromProduct(Product product) {
        if (product instanceof Electronics) {
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothing";
        } else {
            return "Unknown"; // Adjust accordingly for other product types
        }
    }

    // Method to check if the cart is eligible for a 10% discount for the first purchase
    public boolean discountEligible10(){
        if (owner.isFirstPurchase()){
            return true;
        }else {
            return false;
        }
    }

    // Method to calculate the final total after applying discounts
    public double calculateFinalTotal(){
        double total = calculateTotal();
        double discount10 = calculateDiscount10();
        double discount20 = calculateDiscount20();

        return total-(discount10 + discount20);
    }

    // Method to calculate the total price of products in the cart
    public double calculateTotal(){
        double total =0;
        for (Map.Entry<Product,Integer> entry:getProductQuantity().entrySet()){
            Product product = entry.getKey();
            int count = entry.getValue();
            total += product.getProductPrice()*count;
        }
        return total;
    }

    // Method to calculate a 10% discount if eligible
    public double calculateDiscount10(){
        double total = calculateTotal();
        if (discountEligible10()){
            return total*0.1;
        }else {
            return 0;
        }
    }

    // Method to calculate a 20% discount if eligible
    public double calculateDiscount20() {
        double total = calculateTotal();
        if (discountEligible()) {
            return total * 0.2;
        } else {
            return 0;
        }
    }

    /*public void removeProduct(String product_id) {
        //cartProductList.remove(product_id);
        //gui.updateCartTable();
    }*/

}
