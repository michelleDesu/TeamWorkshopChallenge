package se.lexicon.michelle.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.michelle.data.exceptions.ExistingEmailException;
import se.lexicon.michelle.data.exceptions.NonExistingCustomer;
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
        CustomerSequencer.resetCustomerId();

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
    @Test
    public void given_customer_findAccountByCustomer_should_return_account_with_correct_customer() throws NonExistingCustomer {
        BankAccount actual = accountHandler.findAccountByCustomer(firstCustomer);
        assertEquals(firstAccount, actual);

    }
    @Test (expected = NonExistingCustomer.class)
    public void given_customer_findAccountByCustomer_should_throw_NonExistingCustomer() throws NonExistingCustomer {
        Customer thirdCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Erak",
                "Sköld",
                "Erak.Sköld@test.com"
        );
        BankAccount actual = accountHandler.findAccountByCustomer(thirdCustomer);
    }

    @Test
    public void given_bankAccountID_remove_specified_id_return_new_account_array() throws NonExistingId, ExistingEmailException {
        Customer thirdCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Erak",
                "Sköld",
                "Erak.Sköld@test.com"
        );
        BankAccount thirdAccount = accountHandler.addAccount(200, thirdCustomer );

        BankAccount[] expected = new BankAccount[2];
        expected[0] = firstAccount;
        expected[1] = thirdAccount;

        BankAccount[] actual = accountHandler.removeByAccountId(2);
        assertArrayEquals(expected,actual);

    }
    @Test (expected = NonExistingId.class)
    public void given_bankAccountID_remove_specified_id_throws_NonExistingId () throws NonExistingId, ExistingEmailException {
        BankAccount[] actual = accountHandler.removeByAccountId(4);
    }

    @Test
    public void given_CustomerID_remove_specified_id_return_new_account_array() throws NonExistingId, ExistingEmailException {
        Customer thirdCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Erak",
                "Sköld",
                "Erak.Sköld@test.com"
        );
        BankAccount thirdAccount = accountHandler.addAccount(200, thirdCustomer );

        BankAccount[] expected = new BankAccount[2];
        expected[0] = firstAccount;
        expected[1] = thirdAccount;

        BankAccount[] actual = accountHandler.removeByCustomerId(secondAccount.getAccountOwner().getCustomerID());
        assertArrayEquals(expected,actual);

    }

    @Test (expected = NonExistingId.class)
    public void given_customerID_remove_specified_id_throws_NonExistingId () throws NonExistingId{
        Customer thirdCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Erak",
                "Sköld",
                "Erak.Sköld@test.com"
        );
        BankAccount[] actual = accountHandler.removeByCustomerId(thirdCustomer.getCustomerID());
    }

    @Test
    public void given_Customer_remove_specified_customer_return_new_account_array() throws NonExistingId, ExistingEmailException {
        Customer thirdCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Erak",
                "Sköld",
                "Erak.Sköld@test.com"
        );
        BankAccount thirdAccount = accountHandler.addAccount(200, thirdCustomer );

        BankAccount[] expected = new BankAccount[2];
        expected[0] = firstAccount;
        expected[1] = thirdAccount;

        BankAccount[] actual = accountHandler.removeByCustomer(secondCustomer);
        assertArrayEquals(expected,actual);

    }

    @Test (expected = NonExistingId.class)
    public void given_customer_remove_specified_customer_throws_NonExistingCustomer () throws NonExistingId, NonExistingCustomer {
        Customer thirdCustomer = new Customer(
                CustomerSequencer.nextCustomerId(),
                "Erak",
                "Sköld",
                "Erak.Sköld@test.com"
        );
        BankAccount[] actual = accountHandler.removeByCustomer(thirdCustomer);
    }


}
