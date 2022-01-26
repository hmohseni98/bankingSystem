package CustomException;

public class InvalidSourceCard extends RuntimeException{
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public InvalidSourceCard() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Source Card" + TEXT_RESET;
    }
}
