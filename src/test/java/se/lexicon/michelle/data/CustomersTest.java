package se.lexicon.michelle.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.michelle.model.Customer;
import se.lexicon.michelle.data.exceptions.*;

import static org.junit.Assert.*;

public class CustomersTest {
   private Customers customersTest;
   private Customer firstCustomer,
                    secondCustomer;

    @Before
    public void setUp() throws Exception {
        customersTest = new Customers();
        customersTest.clear();
        CustomerSequencer.resetCustomerId();


        firstCustomer = customersTest.addCostumer(
                "Saga" ,
                "Helgadotter",
                "saga.helgadotter@test.com"
        );
        secondCustomer = customersTest.addCostumer(
                "Haldur" ,
                "Rind",
                "Haldur.Rind@test.com"
        );

    }

    @Test
    public void clear_array_should_be_empty() {
        Customer[] expected = new Customer[0];
        customersTest.clear();
        Customer[] actual = customersTest.returnAllCustomers();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void returnAllCostumers_should_return_all_customers() {
        Customer[] expectedArray = new Customer[2];
        expectedArray[0] = firstCustomer;
        expectedArray[1] = secondCustomer;
        Customer[] actual = customersTest.returnAllCustomers();
        assertArrayEquals(expectedArray, actual);
    }

    @Test
    public void addCustomer_given_correct_values_return_new_customer() throws ExistingEmailException {
        Customer expected = new Customer(
               3,
                "Erak",
                "Sköld",
                "Erak.ulvbane@test.com"
                ),
                actual = customersTest.addCostumer(
                        "Erak",
                        "Sköld",
                        "Erak.ulvbane@test.com"
                );

        assertEquals(expected.getCustomerID(), actual.getCustomerID());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());

    }

    @Test (expected = ExistingEmailException.class)
    public void addCustomer_given_already_existing_values_return_null() throws ExistingEmailException {
        Customer thirdCustomer = customersTest.addCostumer(
                "Erak",
                "Sköld",
                "Erak.ulvbane@test.com"
        );
            Customer actual = customersTest.addCostumer(
                    "Erak",
                    "Sköld",
                    "Erak.ulvbane@test.com"
            );

    }

    //find by id

    @Test
    public void given_id_should_return_customer_with_correct_id() throws NonExistingId{
        Customer actual = customersTest.findById(1);
        assertEquals(firstCustomer, actual);
    }

    @Test (expected = NonExistingId.class)
    public void given_incorrect_id_should_throw_NonExistingId() throws NonExistingId{
        Customer actual = customersTest.findById(15);
        assertEquals(firstCustomer, actual);
    }

    //find by e-mail
    @Test
    public void given_email_should_return_customer_with_correct_id() throws NonExistingEmail {
        Customer actual = customersTest.findByEmail("saga.helgadotter@test.com");
        assertEquals(firstCustomer, actual);
    }

    @Test (expected = NonExistingEmail.class)
    public void given_nonexisting_email_should_return_NonExistingEmail() throws NonExistingEmail {
        Customer actual = customersTest.findByEmail("sagan.helgadotter@test.com");
        assertEquals(firstCustomer, actual);
    }



    //remove by id



    //remove by e-mail


}
