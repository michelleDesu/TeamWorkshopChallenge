package se.lexicon.michelle.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerSequencerTest {

    @Before
    public void setUp() throws Exception {
        //only to change the sequence value from another value than one
        CustomerSequencer.resetCustomerId();
        int sequenceValue = CustomerSequencer.nextCustomerId();
    }

    @Test
    public void nextCustomer_should_return_correct_value() {
        int expected = 2,
            actual = CustomerSequencer.nextCustomerId();
            assertEquals(expected, actual);
    }

    @Test
    public void reset_should_set_next_customer_to_one() {
        int expected = 1;
        CustomerSequencer.resetCustomerId();
        int actual = CustomerSequencer.nextCustomerId();
    }
}
