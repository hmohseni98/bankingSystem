import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static String saveCardNumber;
    public static void main(String[] args) throws SQLException {
        /*
        lotfan ghabl az ejra marahel zir ra anjam dahid:
        1. run createEnum.sql
        2. run fakeDB.CreateData va sepas comment kardan an.
         */
        FakeDB fakeDB = new FakeDB();
        //fakeDB.fillData();
            welcomeMenu();


    }
    static void welcomeMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to the bank");
        System.out.println("please select one option");
        System.out.println("1.login");
        System.out.println("2.create account");
        System.out.print("please select one option:");
        Integer number = scanner.nextInt();
        if (number == 1)
            loginMenu();
        else
            signupMenu();
    }

    static void signupMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name:");
        String firstName = scanner.next();
        System.out.print("Enter last name:");
        String lastName = scanner.next();
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer(firstName, lastName);
        customer.setId(customerService.insert(customer));
        BranchService branchService = new BranchService();
        BranchList branchList = new BranchList();
        branchList = branchService.findAll();
        System.out.println("please select one branch");
        branchList.showList();
        System.out.print("enter branch id:");
        Integer branch_id = scanner.nextInt();
        System.out.print("Enter your deposit amount:(ex:10000):");
        Integer amount = scanner.nextInt();
        Branch branch = new Branch();
        branch = branchService.findById(branch_id);
        branch.setId(branch_id);
        Account account = new Account(branch, customer, amount, AccountStatus.active);
        AccountRepository accountRepository = new AccountRepository();
        account.setId(accountRepository.insert(account));
        CreditCardService creditCardService = new CreditCardService();
        creditCardService.insert(account.getId());
        CreditCard creditCard = new CreditCard();
        creditCard = creditCardService.findByAccountId(account.getId());
        System.out.println("your account information:");
        System.out.println(creditCard);
        welcomeMenu();
    }

    static Boolean checkCard(String cardNumber, Integer firstPassword) throws SQLException {
        CreditCard creditCard = null;
        CreditCardService creditCardService = new CreditCardService();
        Boolean result = creditCardService.checkCard(cardNumber, firstPassword);
        if (result == null) {
            System.out.println("your information is not correct!");
            return false;
        }
        if (result) {
            System.out.println("success!");
            saveCardNumber = cardNumber;
            return true;
        }
        return false;
    }

    static void loginMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please enter your card number(def:6037991110124229):");
        String cardNumber = scanner.next();
        Integer firstPassword = null;
        Integer conuter = 3;
        AccountService accountService = new AccountService();
        for (int i = 1; i < 4; i++) {
            System.out.print("please enter your first password(def:1025):");
            firstPassword = scanner.nextInt();
            if (checkCard(cardNumber, firstPassword)){
                mainMenu();
            }
            else{
                conuter--;
                System.out.println( conuter + " Try Left!");
            }
            if (conuter == 0){
                accountService.bannedAccount(cardNumber);
                System.out.println("your account is deActive!");
                break;
            }
        }
    }

    static void mainMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Money transfer");
        System.out.println("2.Change or set second password");
        System.out.println("3.show List of transaction");
        System.out.println("4.back to login menu");
        System.out.print("please select one option:");
        Integer number = scanner.nextInt();
        if (number == 1) {
            moneyTransferMenu(saveCardNumber);
        }
        else if (number == 2){
            defineSecondPassword(saveCardNumber);
        }
        else if (number == 3){
            transactionMenu();
        }
        else if (number == 4)
            welcomeMenu();
    }
    static void transactionMenu() throws SQLException {
        TransactionService transactionService = new TransactionService();
        TransactionList transactionList = new TransactionList();
        System.out.print("input start of date(ex:2022-01-10):");
        Scanner scanner = new Scanner(System.in);
        Date startDate = Date.valueOf(scanner.next());
        transactionList = transactionService.findAllById(startDate,saveCardNumber);
        transactionList.showList();
        mainMenu();
    }
    static void moneyTransferMenu(String srcNumber) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount for transfer:");
        Integer amount = scanner.nextInt();
        System.out.print("Enter destination card Number:");
        String dstNumber = scanner.next();
        CreditCardService creditCardService = new CreditCardService();
        if(creditCardService.checkTransfer(srcNumber,dstNumber,amount)){
            System.out.print("enter second password:");
            Integer secondPassword = scanner.nextInt();
            System.out.print("enter cvv2:");
            Integer cvv2 = scanner.nextInt();
            System.out.print("enter expire date(ex:2026-10-10):");
            Date expireDate = Date.valueOf(scanner.next());
            if (creditCardService.checkTransfer2(saveCardNumber,secondPassword,cvv2,expireDate)) {
                creditCardService.transferAction(amount, srcNumber, dstNumber);
                System.out.println("success");
                mainMenu();
            }
        }
    }
    static void defineSecondPassword(String cardNumber) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your new second password:");
        Integer secondPassword = scanner.nextInt();
        CreditCardService creditCardService = new CreditCardService();
        creditCardService.defineSecondPassword(secondPassword,cardNumber);
        mainMenu();
    }
}
