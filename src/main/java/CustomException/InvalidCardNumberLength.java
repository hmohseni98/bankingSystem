package CustomException;

public class InvalidCardNumberLength extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidCardNumberLength() {
    }

    @Override
    public String toString() {
        return TEXT_RED +"Invalid Card Number Length" + TEXT_RESET;
    }
}