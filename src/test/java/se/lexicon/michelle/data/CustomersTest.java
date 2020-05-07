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


        firstCustomer = customersTest.addCustomer(
                "Saga" ,
                "Helgadotter",
                "saga.helgadotter@test.com"
        );
        secondCustomer = customersTest.addCustomer(
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
                actual = customersTest.addCustomer(
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
        Customer thirdCustomer = customersTest.addCustomer(
                "Erak",
                "Sköld",
                "Erak.ulvbane@test.com"
        );
            Customer actual = customersTest.addCustomer(
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
    public void given_nonExisting_email_should_return_NonExistingEmail() throws NonExistingEmail {
         customersTest.findByEmail("sagan.helgadotter@test.com");
    }


    //remove by id

    @Test
    public void given_CustomerID_remove_specified_id_return_new_customers_array() throws NonExistingId, ExistingEmailException {
        String firsName = "Moda",
                lastName = "Knaphövde",
                email = "moda.knaphövde@test.com";
        Customer thirdCustomer = customersTest.addCustomer(firsName,lastName, email);

        Customer[] expected = new Customer[2];
        expected[0] = firstCustomer;
        expected[1] = thirdCustomer;

        Customer[] actual = customersTest.removeById(2);
        assertArrayEquals(expected,actual);

    }

    //throws exception remove id
    @Test (expected = NonExistingId.class)
    public void given_CustomerID_remove_specified_id_throws_NonExistingId () throws NonExistingId, ExistingEmailException {
        String firsName = "Moda",
                lastName = "Knaphövde",
                email = "moda.knaphövde@test.com";
        Customer thirdCustomer = customersTest.addCustomer(firsName,lastName, email);

        Customer[] expected = new Customer[2];
        expected[0] = firstCustomer;
        expected[1] = thirdCustomer;

        Customer[] actual = customersTest.removeById(4);
        assertArrayEquals(expected,actual);

    }



    //remove by e-mail
    @Test
    public void given_CustomerEmail_remove_specified_email_return_new_customers_array() throws NonExistingEmail, ExistingEmailException {
        String firsName = "Moda",
                lastName = "Knaphövde",
                email = "moda.knaphövde@test.com";
        Customer thirdCustomer = customersTest.addCustomer(firsName,lastName, email);

        Customer[] expected = new Customer[2];
        expected[0] = firstCustomer;
        expected[1] = thirdCustomer;

        Customer[] actual = customersTest.removeByEmail("Haldur.Rind@test.com");
        assertArrayEquals(expected,actual);

    }

    //Throws Error
    @Test (expected = NonExistingEmail.class)
    public void given_CustomerID_remove_specified_Email_throws_NonExistingEmail () throws NonExistingId, ExistingEmailException, NonExistingEmail {
        String firsName = "Moda",
                lastName = "Knaphövde",
                email = "moda.knaphövde@test.com";
        Customer thirdCustomer = customersTest.addCustomer(firsName, lastName, email);

        Customer[] expected = new Customer[2];
        expected[0] = firstCustomer;
        expected[1] = thirdCustomer;

        Customer[] actual = customersTest.removeByEmail("sHaldur.Rind@test.com");
        assertArrayEquals(expected, actual);
    }
}
