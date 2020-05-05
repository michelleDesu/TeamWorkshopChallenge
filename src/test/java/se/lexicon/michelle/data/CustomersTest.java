package se.lexicon.michelle.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.michelle.model.Customer;

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
}
