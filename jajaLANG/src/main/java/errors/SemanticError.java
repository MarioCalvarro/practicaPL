package main.java.errors;

public class SemanticError extends RuntimeException{
    public SemanticError(String message) {
        super("[Semmantic Error] " + message);
    }
}
