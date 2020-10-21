package bts.test.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg, String email) {
        super(String.format(msg, email));
    }
}
