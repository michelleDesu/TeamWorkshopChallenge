package se.lexicon.michelle.data;

public class BankAccountSequencer {
    private static int bankAccountId = 1;

    public static int nextBankAccountId(){
        return bankAccountId++;
    }

    public static void resetBankAccountId(){
        bankAccountId = 1;
    }
}
