package se.lexicon.michelle.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.michelle.model.Customer;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer testCustomer;

    @Before
    public void setUp() throws Exception {
        testCustomer = new Customer(
                1,
                "Saga",
                "Helgadotter av Helgaätt",
                "saga.helgadotter@test.com"
        );
    }


    @Test
    public void testCustomer_has_correct_field() {
        assertEquals( 1, testCustomer.getCustomerID());
        assertEquals("Saga", testCustomer.getFirstName());
        assertEquals("Helgadotter av Helgaätt", testCustomer.getLastName());
        assertEquals( "saga.helgadotter@test.com" , testCustomer.getEmail());
    }
    /**
     * test for get and set methods
     */
    @Test
    public void testCustomer_set_name_set_lastName() {
        testCustomer.setFirstName("Haldur");
        testCustomer.setLastName("Rind");
        assertEquals("Haldur", testCustomer.getFirstName());
        assertEquals("Rind" , testCustomer.getLastName());
    }
}
