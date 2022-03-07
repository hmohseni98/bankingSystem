package CustomException;

public class InvalidDestinationCard extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidDestinationCard() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Destination Card" + TEXT_RESET;
    }
}
