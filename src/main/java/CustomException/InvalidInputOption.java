package CustomException;

public class InvalidInputOption extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidInputOption() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "invalid input option!" + TEXT_RESET;
    }
}
