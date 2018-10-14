package ua.edu.ukma.e_request.utils.exceptions;

public class BadRequestException extends Exception {
    private final String name;

    public BadRequestException(String name) {
        super("Can't resolve parameter "+name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
