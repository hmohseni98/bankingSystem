package CustomException;

public class InsufficientMoney extends RuntimeException{
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public InsufficientMoney() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Insufficient Money" + TEXT_RESET;
    }
}
