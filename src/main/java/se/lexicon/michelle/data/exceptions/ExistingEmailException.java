package se.lexicon.michelle.data.exceptions;

public class ExistingEmailException extends Exception {
    public ExistingEmailException(String errorMessage) {
        super(errorMessage);
    }
}
