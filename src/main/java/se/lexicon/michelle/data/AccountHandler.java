package se.lexicon.michelle.data;

import se.lexicon.michelle.data.exceptions.NonExistingId;
import se.lexicon.michelle.model.BankAccount;
import se.lexicon.michelle.model.Customer;

import java.util.Arrays;

public class AccountHandler {

    private static BankAccount[] bankAccounts = new BankAccount[0];


    public void clear() {
        bankAccounts = new BankAccount[0];
    }

    public int size(){
        return bankAccounts.length;
    }
    /**
     * Creates a new BankAccount and
     * @param balance double
     * @param customer Customer
     * @return BankAccount
     */
    public BankAccount addAccount(double balance, Customer customer){
        BankAccount accountToBeAdded = new BankAccount(
                BankAccountSequencer.nextBankAccountId(),
                balance,
                customer
        );

        /*
        Creates a new array with the old arrays length +1
        This to make the array longer so more objects can be added.
         */
        BankAccount[] tempAccounts = Arrays.copyOf(bankAccounts, bankAccounts.length + 1);
        int offSet = bankAccounts.length;

        //adds the new customer in the new array
        for (int i = offSet; i < tempAccounts.length; i++ ){
            tempAccounts[i] = accountToBeAdded;
        }
        //Sets the old array to the new array
        bankAccounts = tempAccounts;

        return accountToBeAdded;
    }


    public BankAccount[] returnAllBankAccounts() {
        return bankAccounts;
    }

    public BankAccount findAccountByBankAccountId(int accountId) throws NonExistingId {
        BankAccount returnedAccount = null;
        for (BankAccount account: bankAccounts){
            if(account.getAccountID() == accountId){
                returnedAccount = account;
            }
        }
        if (returnedAccount == null){
            throw new NonExistingId("The specified ID does not exist");
        }
        return returnedAccount;
    }

    public BankAccount findAccountByCustomerId(int customerId) throws NonExistingId {
        BankAccount returnedAccount = null;
        for (BankAccount account: bankAccounts){
            if(account.getAccountOwner().getCustomerID() == customerId){
                returnedAccount = account;
            }
        }
        if (returnedAccount == null){
            throw new NonExistingId("The specified ID does not exist");
        }
        return returnedAccount;
    }
}
