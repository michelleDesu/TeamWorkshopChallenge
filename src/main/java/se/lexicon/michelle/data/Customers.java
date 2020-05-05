package se.lexicon.michelle.data;

import com.sun.xml.internal.bind.v2.TODO;
import se.lexicon.michelle.model.Customer;

import java.util.Arrays;

public class Customers {
    private static Customer[] customers = new Customer[0];

    public void clear() {
        customers = new Customer[0];
    }
    public Customer[] returnAllCustomers(){
        return customers;
    }

    public Customer addCostumer(String firstName, String lastName, String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().toLowerCase().equals(email.toLowerCase())) {
                /**
                 * Todo throw IllegalArgumentException() if email exists
                 */
                return null;
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
}
