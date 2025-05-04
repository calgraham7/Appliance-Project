public class ApplianceException extends Exception {

    // Default constructor
    public ApplianceException() {
        super("Appliance error occurred.");
    }

    // Constructor that accepts a custom message
    public ApplianceException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public ApplianceException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public ApplianceException(Throwable cause) {
        super(cause);
    }
}
