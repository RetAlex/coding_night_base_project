package ua.edu.ukma.e_request.utils.exceptions;

/**
 * Created by nasti on 10/14/2018.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super("User with user_id=" + userId + "was not found");
    }
}
