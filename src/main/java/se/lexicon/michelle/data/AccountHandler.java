package se.lexicon.michelle.data;

import se.lexicon.michelle.data.exceptions.NonExistingCustomer;
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


    public BankAccount findAccountByCustomer(Customer customer) throws NonExistingCustomer {
        BankAccount returnedAccount = null;
        for (BankAccount account: bankAccounts){
            if(account.getAccountOwner().getCustomerID() == customer.getCustomerID()){
                returnedAccount = account;
            }
        }
        if (returnedAccount == null){
            throw new NonExistingCustomer("The specified ID does not exist");
        }
        return returnedAccount;
    }

    public BankAccount[] removeByAccountId(int accountId)throws NonExistingId {

        BankAccount[] arrayBeforeAccountId = new BankAccount[0],
                arrayAfterAccountId = new BankAccount[0],
                newAccounts;

        //if customer with id does not exist an exception is thrown.
        int idPos;
        /**
         * fetches the position of the object with the specified id in the array
         * if it does not exist the posID will be the length of customers array
         */

        for (idPos = 0; idPos < bankAccounts.length; idPos++) {
            if (bankAccounts[idPos].getAccountID() == accountId) {
                break;
            }
        }
        //if the specified id does not exist no object can be removed, exception is thrown
        if (idPos == bankAccounts.length) {
            throw new NonExistingId("The specified ID does not exist");
        }
        for (int i = 0; i < idPos; i++) {
            arrayBeforeAccountId = increaseAndAddArray(arrayBeforeAccountId, bankAccounts[i]);
        }
        for (int i = idPos + 1; i < bankAccounts.length; i++) {
            arrayAfterAccountId = increaseAndAddArray(arrayAfterAccountId, bankAccounts[i]);
        }

        /**
         * remove the object with the given ID combine the two arrays
         * arrayBeforeTodoId and arrayAfterTodoId
         */
        newAccounts = Arrays.copyOf(
                arrayBeforeAccountId,
                arrayBeforeAccountId.length + arrayAfterAccountId.length
        );
        System.arraycopy(
                arrayAfterAccountId,
                0,
                newAccounts,
                arrayBeforeAccountId.length,
                arrayAfterAccountId.length
        );

        bankAccounts = newAccounts;
        return bankAccounts;

    }
    public BankAccount[] removeByCustomerId(int customerID) throws NonExistingId {
        BankAccount[] arrayBeforeAccountId = new BankAccount[0],
                arrayAfterAccountId = new BankAccount[0],
                newAccounts;

        //if customer with id does not exist an exception is thrown.
        int idPos;
        /**
         * fetches the position of the object with the specified id in the array
         * if it does not exist the posID will be the length of customers array
         */

        for (idPos = 0; idPos < bankAccounts.length; idPos++) {
            if (bankAccounts[idPos].getAccountOwner().getCustomerID() == customerID) {
                break;
            }
        }
        //if the specified id does not exist no object can be removed, exception is thrown
        if (idPos == bankAccounts.length) {
            throw new NonExistingId("The specified ID does not exist");
        }
        for (int i = 0; i < idPos; i++) {
            arrayBeforeAccountId = increaseAndAddArray(arrayBeforeAccountId, bankAccounts[i]);
        }
        for (int i = idPos + 1; i < bankAccounts.length; i++) {
            arrayAfterAccountId = increaseAndAddArray(arrayAfterAccountId, bankAccounts[i]);
        }

        /**
         * remove the object with the given ID combine the two arrays
         * arrayBeforeTodoId and arrayAfterTodoId
         */
        newAccounts = Arrays.copyOf(
                arrayBeforeAccountId,
                arrayBeforeAccountId.length + arrayAfterAccountId.length
        );
        System.arraycopy(
                arrayAfterAccountId,
                0,
                newAccounts,
                arrayBeforeAccountId.length,
                arrayAfterAccountId.length
        );

        bankAccounts = newAccounts;
        return bankAccounts;

    }

    public BankAccount[] removeByCustomer(Customer customer) throws NonExistingId {
        BankAccount[] arrayBeforeAccountId = new BankAccount[0],
                arrayAfterAccountId = new BankAccount[0],
                newAccounts;

        //if customer with id does not exist an exception is thrown.
        int idPos;
        /**
         * fetches the position of the object with the specified id in the array
         * if it does not exist the posID will be the length of customers array
         */

        for (idPos = 0; idPos < bankAccounts.length; idPos++) {
            if (bankAccounts[idPos].getAccountOwner().getCustomerID() == customer.getCustomerID()) {
                break;
            }
        }
        //if the specified id does not exist no object can be removed, exception is thrown
        if (idPos == bankAccounts.length) {
            throw new NonExistingId("The specified ID does not exist");
        }
        for (int i = 0; i < idPos; i++) {
            arrayBeforeAccountId = increaseAndAddArray(arrayBeforeAccountId, bankAccounts[i]);
        }
        for (int i = idPos + 1; i < bankAccounts.length; i++) {
            arrayAfterAccountId = increaseAndAddArray(arrayAfterAccountId, bankAccounts[i]);
        }

        /**
         * remove the object with the given ID combine the two arrays
         * arrayBeforeTodoId and arrayAfterTodoId
         */
        newAccounts = Arrays.copyOf(
                arrayBeforeAccountId,
                arrayBeforeAccountId.length + arrayAfterAccountId.length
        );
        System.arraycopy(
                arrayAfterAccountId,
                0,
                newAccounts,
                arrayBeforeAccountId.length,
                arrayAfterAccountId.length
        );

        bankAccounts = newAccounts;
        return bankAccounts;

    }

    private BankAccount[] increaseAndAddArray(BankAccount[] toIncreaseArray, BankAccount accountToAdd){

        toIncreaseArray = Arrays.copyOf(toIncreaseArray, toIncreaseArray.length +1);
        toIncreaseArray[toIncreaseArray.length -1] = accountToAdd;
        return toIncreaseArray;
    }


}
