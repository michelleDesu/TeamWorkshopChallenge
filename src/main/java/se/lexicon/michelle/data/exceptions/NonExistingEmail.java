package se.lexicon.michelle.data.exceptions;

public class NonExistingEmail extends Exception {
    public NonExistingEmail(String errorMessage) {
        super(errorMessage);
    }
}
