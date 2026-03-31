package lk.ijse.vehicleservice.exception.custom;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}