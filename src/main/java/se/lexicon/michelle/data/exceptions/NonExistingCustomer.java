package se.lexicon.michelle.data.exceptions;

public class NonExistingCustomer extends Throwable {
    public NonExistingCustomer(String errorMessage) {
        super(errorMessage);
    }
}
