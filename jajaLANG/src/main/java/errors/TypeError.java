package main.java.errors;

public class TypeError extends RuntimeException {
    public TypeError(String message) {
        super("[Type Error] " + message);
    }
}
