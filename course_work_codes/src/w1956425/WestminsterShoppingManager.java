package w1956425;

import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager, Serializable {

    private List<Product> systemProductList;
    private List<Product> sortedList;
    private List<User> regUserList;

    // Constructor for WestminsterShoppingManager
    public WestminsterShoppingManager() {
        this.systemProductList = new ArrayList<>();
        this.regUserList = new ArrayList<>();
        loadFromFile();
    }

    // Getter method to retrieve a sorted list of products
    public List<Product> getSortedList() {
        sortedList = new ArrayList<>(systemProductList);
        Collections.sort(sortedList, Comparator.comparing(Product::getProductID));
        return sortedList;
    }

    //Method to add a product to the system
    @Override
    public void addProductToSystem(Product product) {
        if (systemProductList.size() < 50) {
            systemProductList.add(product);
        } else {
            System.out.println("Maximum limit reached. Cannot add the product! ");
        }
    }

    //Method to delete a product from the system based on its ID

    public void deleteProductFromSystem(String product_id) {
        Iterator<Product> iterator = systemProductList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID().equals(product_id)) {
                iterator.remove();
                String productType = (product instanceof Electronics) ? "Electronics" : "Clothing";
                System.out.println("Product ID: " + product_id + " in " + productType + " is deleted");
                System.out.println("The total number of products left in the system: " + systemProductList.size());
                return;
            }
        }
    }

    //Method to print the sorted list of products to the console
    @Override
    public void printSystemProductList() {
        System.out.println("***** Product List *****");
        sortedList = new ArrayList<>(systemProductList);
        Collections.sort(sortedList, Comparator.comparing(Product::getProductID));
        for (Product product : sortedList) {
            System.out.println(product.printInfo());
            String productType = (product instanceof Electronics) ? "Electronics" : "Clothing";
            System.out.println("Product is a " + productType);
        }
    }

    //Method to save the system product list to a file.
    @Override
    public void saveInFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productsList.txt"))) {
            for (Product product : systemProductList) {
                writer.write(product.printInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to load product data from a file into the system
    @Override
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("productsList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] readList = line.split(",");
                String product_id = readList[0];
                String product_name = readList[1];
                int number_of_available_items = Integer.parseInt(readList[2]);
                double price = Double.parseDouble(readList[3]);

                if (readList[6].startsWith("E")) {
                    int electronic_warrantyPeriod = Integer.parseInt(readList[4]);
                    String electronic_brand = readList[5];
                    systemProductList.add(new Electronics(product_id, product_name, number_of_available_items, price, electronic_brand, electronic_warrantyPeriod));
                } else if (readList[6].startsWith("C")) {
                    String clothing_size = readList[4];
                    String clothing_colour = readList[5];
                    systemProductList.add(new Clothing(product_id, product_name, number_of_available_items, price, clothing_size, clothing_colour));
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("...Creating new list...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to display the main menu options
    public static void displayMenu() {
        System.out.println("***** Shopping Manager *****");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of the all products");
        System.out.println("4. Save products in a file");
        System.out.println("5. Open the GUI");
        System.out.println("6. Exit from the programme");
        System.out.println("Select an option 1/2/3/4/5/6 : ");
    }

    //Method to register a new user with a username and password
    public void registerUser(String username, String password) {
        User newUser = new User(username, password);
        regUserList.add(newUser);
    }

    // Method to authenticate a user based on username and password
    public boolean userAuthentication(String username, String password) {
        for (User user : regUserList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to save registered users to a file
    public void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("registeredUsers.dat", false))) {
            oos.writeObject(regUserList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load registered users from a file
    public void loadRegUserList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("registeredUsers.dat"))) {
            regUserList = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("...Creating new file...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get a user by their username
    public User getUserByUsername(String username) {
        for (User user : regUserList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
