package se.lexicon.michelle.model;

import se.lexicon.michelle.data.exceptions.*;

public class BankAccount {
    private final int   accountID;
    private double      balance;
    private Customer    accountOwner;

    public BankAccount(int accountID, double balance, Customer accountOwner) {
        this.accountID = accountID;
        this.balance = balance;
        this.accountOwner = accountOwner;
    }

    /**
     * getters
     */
    public int getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getAccountOwner() {
        return accountOwner;
    }

    /**
     * setters
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountOwner(Customer accountOwner) {
        this.accountOwner = accountOwner;
    }

    public double deposit(double deposit) throws IllegalAmountException {
        if (deposit < 0){
            throw  new IllegalAmountException("The specified amount is not valid " + deposit);
        }
        balance += deposit;
        return balance;
    }

    public double withdraw(double withdraw) throws IllegalAmountException {
        if( withdraw < 0 ){
            throw  new IllegalAmountException ("The specified amount is not valid " + withdraw);
        }else if((balance-withdraw )< 0){
            throw  new IllegalAmountException("The specified withdrawal is higher than your current balance " + withdraw);
        }
        balance -= withdraw;
        return balance;
    }
}
