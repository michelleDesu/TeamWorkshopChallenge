package se.lexicon.michelle.data.exceptions;

public class NonExistingId  extends Exception {
    public NonExistingId(String errorMessage) {
        super(errorMessage);
    }
}
