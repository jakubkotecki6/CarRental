package pl.sda.carrental;

public class ObjectNotFoundInRepositoryException extends RuntimeException {
    public ObjectNotFoundInRepositoryException(String message) {
        super(message);
    }
}
