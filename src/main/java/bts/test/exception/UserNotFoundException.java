package bts.test.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(String.format("Not found user with email %s", email));
    }

    public UserNotFoundException(Long id) {
        super(String.format("Not found user with id %s", id));
    }
}
