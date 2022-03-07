package CustomException;

public class InvalidSourceCard extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidSourceCard() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Source Card" + TEXT_RESET;
    }
}
