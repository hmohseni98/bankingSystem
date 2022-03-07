package CustomException;

public class InvalidCharacter extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidCharacter() {

    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Character" + TEXT_RESET;
    }
}
