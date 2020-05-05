package se.lexicon.michelle.data;

public class CustomerSequencer {
    private static int customerId = 1;

    public static int nextCustomerId(){
        return customerId++;
    }

    public static void resetCustomerId(){
        customerId = 1;
    }

}
