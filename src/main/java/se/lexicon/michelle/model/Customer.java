package se.lexicon.michelle.model;

public class Customer {
    private String firstName,
                    lastName;
    private final String  email;
    private final int     customerID;

    /**
     * Constructor
     * @param customerId int
     * @param firstName String
     * @param lastName String
     * @param email String
     */
    public Customer(int customerId, String firstName, String lastName, String email){
        this.customerID = customerId;
        this.email = email;
        setFirstName(firstName);
        setLastName(lastName);
    }


    /**
     * getters
     */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public int getCustomerID() {
        return customerID;
    }

    /**
     *Setters
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}
