package se.lexicon.michelle.data;

import se.lexicon.michelle.model.Customer;
import se.lexicon.michelle.data.exceptions.*;

import java.util.Arrays;

public class Customers {
    private static Customer[] customers = new Customer[0];

    /**
     * clears the entire customers array, mostly for testing usage
     */
    public void clear() {
        customers = new Customer[0];
    }

    /**
     * returns all the customers, if the entire list is needed
     * @return customers array
     */
    public Customer[] returnAllCustomers(){
        return customers;
    }

    /**
     * adding a new customer to customers and increases the array so that more items can be added.
     * @param firstName String
     * @param lastName String
     * @param email String
     * @return Customer the newly created customer.
     * throws "ExistingEmailException" if email already exists
     */
    public Customer addCostumer(String firstName, String lastName, String email) throws ExistingEmailException {
        for (Customer customer : customers) {
            if (customer.getEmail().toLowerCase().equals(email.toLowerCase())) {
                throw new ExistingEmailException("The specified e-mail does already exist");
            }
        }
        Customer customerToAdd = new Customer(
                CustomerSequencer.nextCustomerId(),
                firstName,
                lastName,
                email
        );
        //creates new customer array, this to be able to make the old one larger
        Customer[] newCustomers = Arrays.copyOf(customers, customers.length +1);
        int offSet = customers.length;

        //adds the new customer in the new array
        for (int i = offSet; i < newCustomers.length; i++ ){
            newCustomers[i] = customerToAdd;
        }
        //Sets the old array to the new array
        customers = newCustomers;

        return customerToAdd;
    }

    /**
     * finds and returns the customer with the specified id
     * @param customerId int
     * @return Customer
     * throws "NonExistingId" if the Id does not exist in array
     */
    public Customer findById(int customerId) throws NonExistingId {
        Customer returnedCustomer = null;
        for (Customer customer: customers){
            if(customer.getCustomerID() == customerId){
                returnedCustomer = customer;
            }
        }
        if (returnedCustomer == null){
            throw new NonExistingId("The specified ID does not exist");
        }
        return returnedCustomer;
    }

    /**
     * finds and returns the customer with the specified email
     * @param customerEmail String
     * @return Customer
     * throws "NonExistingEmail" if email not existing in the array.
     */
    public Customer findByEmail(String customerEmail) throws NonExistingEmail {
        Customer returnedCustomer = null;
        for (Customer customer: customers){
            if(customer.getEmail().equals(customerEmail)){
                returnedCustomer = customer;
            }
        }
        if (returnedCustomer == null){
            throw new NonExistingEmail("The specified email does not exist");
        }
        return returnedCustomer;
    }


}
