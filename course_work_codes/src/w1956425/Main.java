package w1956425;
import java.util.Scanner;
import javax.swing.*;

public class Main {

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Main method where the program execution begins
    public static void main(String[] args) {
        // Create an instance of WestminsterShoppingManager to manage the shopping system
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Continuous loop for the main menu
        while (true) {
            // Display the main menu
            displayMainMenu();

            // Check if the user input is an integer
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt(); // Get the user's choice
                // Handle the chosen option from the main menu
                handleMainMenuOption(shoppingManager, option);
            } else {
                System.out.println("Please enter a number");
                scanner.next(); // consume invalid input
            }
        }
    }

    // Method to display the main menu options
    private static void displayMainMenu() {
        WestminsterShoppingManager.displayMenu();
    }

    // Method to handle the user's choice from the main menu
    private static void handleMainMenuOption(WestminsterShoppingManager shoppingManager, int option) {
        switch (option) {
            case 1:
                addProduct(shoppingManager);
                break;
            case 2:
                deleteProduct(shoppingManager);
                break;
            case 3:
                shoppingManager.printSystemProductList();
                break;
            case 4:
                shoppingManager.saveInFile();
                System.out.println("Successfully Saved to File");
                break;
            case 5:
                handleUserAuthentication(shoppingManager);
                break;
            case 6:
                System.out.println("...Exiting the program. Goodbye!...");
                System.exit(0);
            default:
                System.out.println("Invalid selection. Try again !!! (Please enter a number between 1 and 6)");
        }
    }

    // Method to add a product to the shopping system
    private static void addProduct(WestminsterShoppingManager shoppingManager) {
        System.out.println("Enter the product ID: ");
        String product_id = scanner.next();

        System.out.println("Enter product name: ");
        String product_name = scanner.next();
        scanner.nextLine();

        System.out.println("Enter product type (Electronics / Clothing): ");
        String productType = scanner.next();
        scanner.nextLine();

        System.out.println("Enter number of available items: ");
        int number_of_available_items = getValidIntInput();

        System.out.println("Enter price: ");
        double price = getValidDoubleInput();

        if (productType.equalsIgnoreCase("Electronics")) {
            addElectronicsProduct(shoppingManager, product_id, product_name, number_of_available_items, price);
        } else if (productType.equalsIgnoreCase("Clothing")) {
            addClothingProduct(shoppingManager, product_id, product_name, number_of_available_items, price);
        } else {
            System.out.println("Invalid product type");
        }
    }

    // Method to add an electronics product to the shopping system
    private static void addElectronicsProduct(WestminsterShoppingManager shoppingManager,
                                              String product_id, String product_name, int number_of_available_items, double price) {
        System.out.println("Enter brand: ");
        String electronic_brand = scanner.next();

        System.out.println("Enter warranty period: ");
        int electronic_warrantyPeriod = getValidIntInput();

        Product product = new Electronics(product_id, product_name, number_of_available_items, price, electronic_brand, electronic_warrantyPeriod);
        shoppingManager.addProductToSystem(product);
    }

    // Method to add a clothing product to the shopping system
    private static void addClothingProduct(WestminsterShoppingManager shoppingManager,
                                           String product_id, String product_name, int number_of_available_items, double price) {
        System.out.println("Enter size: ");
        String clothing_size = scanner.next();

        System.out.println("Enter colour: ");
        String clothing_colour = scanner.next();

        Product product = new Clothing(product_id, product_name, number_of_available_items, price, clothing_size, clothing_colour);
        shoppingManager.addProductToSystem(product);
    }

    // Method to delete a product from the shopping system
    private static void deleteProduct(WestminsterShoppingManager shoppingManager) {
        System.out.println("Enter product ID: ");
        String productIDDelete = scanner.next();
        shoppingManager.deleteProductFromSystem(productIDDelete);
    }

    // Method to handle user authentication (login or registration)
    private static void handleUserAuthentication(WestminsterShoppingManager shoppingManager) {
        shoppingManager.loadRegUserList();
        System.out.println("Opening GUI");

        System.out.println("1. Log in as a user");
        System.out.println("2. Register as a new user");
        System.out.println("Enter your choice: ");
        int loginOrRegister = scanner.nextInt();

        switch (loginOrRegister) {
            case 1:
                handleUserLogin(shoppingManager);
                break;
            case 2:
                handleUserRegistration(shoppingManager);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
                break;
        }
    }

    // Method to handle user login
    private static void handleUserLogin(WestminsterShoppingManager shoppingManager) {
        System.out.println("Enter your username: ");
        String username = scanner.next();
        System.out.println("Enter your password: ");
        String password = scanner.next();

        if (shoppingManager.userAuthentication(username, password)) {
            System.out.println("Login successful");
            User loggedInUser = shoppingManager.getUserByUsername(username);
            openGUI(shoppingManager, loggedInUser);
        } else {
            System.out.println("Sorry! Login failed. Invalid username or password.");
        }
    }

    // Method to handle user registration
    private static void handleUserRegistration(WestminsterShoppingManager shoppingManager) {
        System.out.println("Enter a new username: ");
        String newUsername = scanner.next();

        System.out.println("Enter a password: ");
        String newPassword = scanner.next();

        shoppingManager.registerUser(newUsername, newPassword);
        shoppingManager.saveUsersToFile();

        System.out.println("The user registered successfully. You can now log in to your cart.");
    }

    // Method to open the GUI for the shopping system
    private static void openGUI(WestminsterShoppingManager manager, User loggedInUser) {
        SwingUtilities.invokeLater(() -> new ShoppingGUI(manager, loggedInUser).setVisible(true));
    }

    // Method to get a valid integer input from the user
    private static int getValidIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("This is an invalid input. Please enter a valid integer.");
            scanner.next(); // consume invalid input
        }
        return scanner.nextInt();
    }

    // Method to get a valid double input from the user
    private static double getValidDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("This is an invalid input. Please enter a valid double.");
            scanner.next(); // consume invalid input
        }
        return scanner.nextDouble();
    }
}
