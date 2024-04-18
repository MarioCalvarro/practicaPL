package main.java.errors;

public class ImportError extends RuntimeException {
    public ImportError(String message) {
        super("[Import Error] " + message);
    }
}
