package w1956425;

import java.io.Serializable;

public class User implements Serializable {

    // Instance variables to store user information
    private String username;
    private String password;
    private boolean firstPurchase;

    // Constructor to initialize user attributes
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.firstPurchase=true; // Assume it's the user's first purchase by default
    }

    // Getter method for retrieving the username
    public String getUsername() {
        return this.username;
    }

    // Setter method for updating the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for retrieving the password
    public String getPassword() {
        return this.password;
    }

    // Setter method for updating the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method for checking if it's the user's first purchase
    public boolean isFirstPurchase(){
        return firstPurchase;
    }

    // Setter method for updating the firstPurchase status
    public void setFirstPurchase(boolean firstPurchase){
        this.firstPurchase=firstPurchase;
    }

}