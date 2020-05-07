package se.lexicon.michelle.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.michelle.data.exceptions.NonExistingId;
import se.lexicon.michelle.model.BankAccount;
import se.lexicon.michelle.model.Customer;

import static org.junit.Assert.*;

public class AccountHandlerTest {

    private AccountHandler  accountHandler;
    private BankAccount     firstAccount,
                            secondAccount;
    private Customer        firstCustomer,
                            secondCustomer;

    @Before
    public void setUp() throws Exception {
        accountHandler = new AccountHandler();
        accountHandler.clear();
        BankAccountSequencer.resetBankAccountId();

        /**
         * BankAccount needs Id, balance, customer
         */
        firstCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Saga",
                "HelgaDotter",
                "Saga.Helgadotter@test.com"
        );
        secondCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Moda",
                "Knaphövde",
                "Moda.Knaphövde@test.com"
        );
        firstAccount = accountHandler.addAccount(100, firstCustomer);
        secondAccount = accountHandler.addAccount( 100, secondCustomer);
    }

    @Test
    public void clear_should_clear_all_accounts() {
        accountHandler.clear();
        int expected = 0,
            actual = accountHandler.size();

       assertEquals(expected,actual);
    }

    @Test
    public void findAll_should_return_all_objects_in_Array() {
        BankAccount[] expected = new BankAccount[2];
        expected[0] = firstAccount;
        expected[1] = secondAccount;

        assertArrayEquals(expected, accountHandler.returnAllBankAccounts());

    }

    @Test
    public void given_id_findAccountById_should_return_account_with_correctId() throws NonExistingId {
        BankAccount actual = accountHandler.findAccountByBankAccountId(1);
        assertEquals(firstAccount, actual);

    }
    @Test (expected = NonExistingId.class)
    public void given_incorrect_id_findAccountById_should_throw_NonExistingId() throws NonExistingId {
        BankAccount actual = accountHandler.findAccountByBankAccountId(0);
        assertEquals(firstAccount, actual);

    }

    @Test
    public void given_customer_id_findAccountById_should_return_account_with_correctId() throws NonExistingId {
        BankAccount actual = accountHandler.findAccountByCustomerId(1);
        assertEquals(firstAccount, actual);

    }
    @Test (expected = NonExistingId.class)
    public void given_incorrect_customer_id_findAccountById_should_throw_NonExistingId() throws NonExistingId {
        BankAccount actual = accountHandler.findAccountByCustomerId(0);
        assertEquals(firstAccount, actual);

    }
}
