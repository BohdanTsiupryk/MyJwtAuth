package bts.test.exception;

public class BadPasswordException extends RuntimeException {
    public BadPasswordException(String email) {
        super(String.format("Bad password for user email - %s", email));
    }
}
