package CustomException;

public class SecondPasswordNotCorrect extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public SecondPasswordNotCorrect() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Second Password Not Correct" + TEXT_RESET;
    }
}
