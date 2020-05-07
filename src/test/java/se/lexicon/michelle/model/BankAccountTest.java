package se.lexicon.michelle.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.michelle.data.BankAccountSequencer;
import se.lexicon.michelle.data.exceptions.IllegalAmountException;

import static org.junit.Assert.*;

public class BankAccountTest {
    BankAccount testBankAccount;
    Customer    testCustomer;
    @Before
    public void setUp() throws Exception {

        testCustomer = new Customer(
                1,
                "Saga",
                "Helgadotter",
                "saga.helgadotter@test.com"
        );
        testBankAccount = new BankAccount(1, 100, testCustomer );
    }

    @Test
    public void testBankAccount_should_has_correct_fields() {
        assertEquals(1 , testBankAccount.getAccountID());
        assertEquals(100, testBankAccount.getBalance(), 0.001);
        assertEquals(testCustomer, testBankAccount.getAccountOwner());

    }

    /**
     * test for get and set methods
     */
    @Test
    public void testCustomer_setAccountOwner() {
        Customer secondTestCustomer = new Customer(
                2,
                "Haldur",
                "Rind",
                "Haldur.Rind@test.com"
        );
        testBankAccount.setAccountOwner(secondTestCustomer);

        assertEquals( secondTestCustomer , testBankAccount.getAccountOwner() );
    }

    @Test
    public void setBalance_set_balance() {
        double balance = 200;
        testBankAccount.setBalance(balance);
        assertEquals(balance, testBankAccount.getBalance(), 0.001);
    }

    @Test
    public void given_balance_deposit_should_return_correct_value() throws IllegalAmountException {
        double depositBalance = 350.50,
        expected = 450.50;
        testBankAccount.deposit(depositBalance);
        assertEquals(expected, testBankAccount.getBalance(), 0.001);
    }

    @Test(expected = IllegalAmountException.class)
    public void given_incorrect_balance_deposit_should_throw_exception()  throws IllegalAmountException{
        double depositBalance = -1;
        testBankAccount.deposit(depositBalance);
    }
    @Test
    public void given_balance_withdrawal_should_return_correct_value() throws IllegalAmountException {
        double withdrawalBalance = 50.50,
                expected = 49.50;
        testBankAccount.withdraw(withdrawalBalance);
        assertEquals(expected, testBankAccount.getBalance(), 0.001);
    }

    @Test(expected = IllegalAmountException.class)
    public void given_incorrect_balance_withdrawal_should_throw_exception()  throws IllegalAmountException{
        double withdrawalBalance = -1,
                secondWithdrawal = 300;
        testBankAccount.withdraw(withdrawalBalance);
        testBankAccount.withdraw(secondWithdrawal);
    }

}
