package CustomException;

public class InvalidInputOption extends RuntimeException{
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public InvalidInputOption() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "invalid input option!" + TEXT_RESET;
    }
}
