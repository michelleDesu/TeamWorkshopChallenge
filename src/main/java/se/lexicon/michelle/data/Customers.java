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


    /**
     * removes the customer with the specified id
     * @param customerId int
     * @return customers
     * @throws "NonExistingId" if id does not exist
     */
    public Customer[] removeById(int customerId) throws NonExistingId {
        Customer [] arrayBeforeCustomerId = new Customer[0],
                    arrayAfterCustomerId = new Customer[0],
                    newCustomers;

        //if customer with id does not exist an exception is thrown.
        //Customer testCustomer = findById(customerId);
        int idPos;

        /**
         * fetches the position of the object with the specified id in the array
         * if it does not exist the posID will be the length of customers array
         */

        for( idPos = 0 ; idPos < customers.length ; idPos++){
            if(customers[idPos].getCustomerID() == customerId){
                break;
            }
        }
        //if the specified id does not exist no object can be removed, exception is thrown
        if(idPos == customers.length){
            throw new NonExistingId("The specified ID does not exist");
        }
        for(int i = 0; i< idPos; i++){
            arrayBeforeCustomerId = increaseAndAddArray(arrayBeforeCustomerId,customers[i] );
        }
        for (int i = idPos + 1; i < customers.length; i++){
            arrayAfterCustomerId = increaseAndAddArray(arrayAfterCustomerId, customers[i]);
        }

        /**
         * remove the object with the given ID combine the two arrays
         * arrayBeforeTodoId and arrayAfterTodoId
         */
        newCustomers = Arrays.copyOf(
                arrayBeforeCustomerId,
                arrayBeforeCustomerId.length + arrayAfterCustomerId.length
                );
        System.arraycopy(
                arrayAfterCustomerId,
                0,
                newCustomers,
                arrayBeforeCustomerId.length,
                arrayAfterCustomerId.length
        );

       customers = newCustomers;
        return customers;

    }

    /**
     * removes the customer with the specified e-mail
     * @param customerEmail String
     * @return customers
     * @throws "NonExistingEmail" if e-mail does not exist
     */
   public Customer[] removeByEmail(String customerEmail) throws NonExistingEmail {
        Customer[] beforeCustomerEmail = new Customer[0],
                    afterCustomerEmail = new Customer[0],
                    newTempCustomers;
        int eMailPos;
       /**
        *  when the correct pos for the email is found break
        *  this is to know exactly where in customers the object is
        *
        */
        for( eMailPos = 0; eMailPos < customers.length; eMailPos++){
            if(customers[eMailPos].getEmail().equals(customerEmail)){
                break;
            }
        }
       /**
        *  if eMailPos == customers.length throw exception,
        *  email does not exist in customers.
        */
       if (eMailPos == customers.length){
           throw new NonExistingEmail("The Specified email does not exist");
       }
       for (int i = 0; i < eMailPos; i++){
           beforeCustomerEmail = increaseAndAddArray(beforeCustomerEmail, customers[i]);
       }
       for(int i = eMailPos +1 ; i < customers.length; i++){
           afterCustomerEmail = increaseAndAddArray(afterCustomerEmail, customers[i]);
       }
       newTempCustomers = Arrays.copyOf(beforeCustomerEmail,beforeCustomerEmail.length + afterCustomerEmail.length);
       System.arraycopy(
               afterCustomerEmail,
               0,
               newTempCustomers,
               beforeCustomerEmail.length,
               afterCustomerEmail.length
               );
       customers = newTempCustomers;
       return customers;
   }


    private Customer[] increaseAndAddArray(Customer[] toIncreaseArray, Customer customerToAdd){

        toIncreaseArray = Arrays.copyOf(toIncreaseArray, toIncreaseArray.length +1);
        toIncreaseArray[toIncreaseArray.length -1] = customerToAdd;
        return toIncreaseArray;
    }


}
