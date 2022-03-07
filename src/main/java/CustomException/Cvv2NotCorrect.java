package CustomException;

public class Cvv2NotCorrect extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public Cvv2NotCorrect() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Cvv2 Not Correct" + TEXT_RESET;
    }
}
