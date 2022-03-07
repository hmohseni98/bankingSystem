package CustomException;

public class SecondPasswordNotDefine extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public SecondPasswordNotDefine() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Second Password Not Define" + TEXT_RESET;
    }
}
