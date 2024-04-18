package main.java.errors;

public class BindError extends RuntimeException {
    public BindError(String message) {
        super("[Bind Error] " + message);
    }
}
