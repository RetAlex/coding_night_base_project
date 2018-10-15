package ua.edu.ukma.e_request.utils.exceptions;

public class RequestNotExistsException extends Exception {
    public RequestNotExistsException(String message) {
        super(message);
    }

    public RequestNotExistsException() {
        super();
    }
}
