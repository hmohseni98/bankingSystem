import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreditCardService {
    private CreditCardRepository creditCardRepository = new CreditCardRepository();
    private AccountService accountService = new AccountService();

    public CreditCardService() throws SQLException {
    }
    public void insert(Integer account_id) throws SQLException {
        Account account;
        account = accountService.findById(account_id);
        CreditCard creditCard;
        String cardNumber = String.valueOf(createCardNumber());
        Integer firstPassword = createFirstPassword();
        Integer cvv2 = createCvv2();
        creditCard = new CreditCard(null,account,cardNumber,firstPassword,-1,cvv2,Date.valueOf("2026-10-10"));
        creditCardRepository.insert(creditCard);
    }
    public void update(Integer creditCard_id,Integer secondPassword) throws SQLException{
        CreditCard creditCard;
        creditCard = creditCardRepository.findById(creditCard_id);
        creditCard.setSecondPassword(secondPassword);
        creditCardRepository.update(creditCard);
    }
    public void delete(Integer creditCard_id) throws SQLException {
        CreditCard creditCard;
        creditCard = creditCardRepository.findById(creditCard_id);
        creditCardRepository.delete(creditCard);
    }
    public CreditCardList findAll() throws SQLException {
        CreditCardList creditCardList;
        creditCardList =creditCardRepository.findAll();
        return creditCardList;
    }
    public CreditCard findById(Integer creditCard_id) throws SQLException {
        CreditCard creditCard;
        creditCard =creditCardRepository.findById(creditCard_id);
        return creditCard;
    }
    public Integer findByCardNumber(String cardNumber) throws SQLException{
        CreditCard creditCard;
        creditCard = creditCardRepository.findByCardNumber(cardNumber);
        return creditCard.getAccount().getId();
    }
    public CreditCard findByAccountId(Integer account_id) throws SQLException{
        CreditCard creditCard;
        creditCard = creditCardRepository.findByAccountId(account_id);
        return creditCard;
    }
    public Boolean checkTransfer(String srcCard, String dstCard, Integer amount) throws SQLException{
        CreditCard source;
        source = creditCardRepository.findByCardNumber(srcCard);
        if (source == null) {
            System.out.println("source card is not exist");
            return false;
        }
        CreditCard destination;
        destination = creditCardRepository.findByCardNumber(dstCard);
        if (destination == null){
            System.out.println("destination is not exist");
            return false;
        }
        if (!accountService.checkBalance(source.getAccount().getId(),amount)){
            System.out.println("not enough money!");
            return false;
        }
        return true;
    }
    public Boolean checkTransfer2(String srcCard,Integer secondPassword,Integer cvv2,Date exDate) throws SQLException {
        CreditCard source;
        source = creditCardRepository.findByCardNumber(srcCard);
        if (source.getSecondPassword().equals(-1)){
            System.out.println("you don't define second password!");
            return false;
        }
        if (!source.getSecondPassword().equals(secondPassword)){
            System.out.println("second password is not correct!");
            return false;
        }
        if (!source.getCvv2().equals(cvv2)){
            System.out.println("cvv2 is not correct!");
            return false;
        }
        if (source.getExpireDate().equals(exDate) && LocalDate.now().isBefore(LocalDate.parse(String.valueOf(exDate))))
            return true;
        return true;
    }
    public void transferAction(Integer amount,String srcCard,String dstCard) throws SQLException {
        CreditCard source;
        source = creditCardRepository.findByCardNumber(srcCard);
        CreditCard destination;
        destination = creditCardRepository.findByCardNumber(dstCard);
        accountService.withdraw(amount,source.getAccount().getId());
        accountService.deposit(amount,destination.getAccount().getId());
    }
    public void defineSecondPassword(Integer secondPassword,String cardNumber) throws SQLException {
        CreditCard source;
        source = creditCardRepository.findByCardNumber(cardNumber);
        source.setSecondPassword(secondPassword);
        update(source.getId(),source.getSecondPassword());
    }
    public Boolean checkCard(String cardNumber,Integer firstPassword) throws SQLException {
        CreditCard creditCard = null;
        creditCard = creditCardRepository.findByCardNumber(cardNumber);
        if (creditCard == null)
            return false;
        if (creditCard.getAccount().getStatus() == AccountStatus.deactive){
            System.out.println("your account is deActive!");
            Main.welcomeMenu();
        }
        if (creditCard.getFirstPassword().equals(firstPassword))
            return true;
        return false;
    }

    private static BigInteger createCardNumber(){
        BigInteger cardNumber = new BigInteger("6037991172641020");
        int one = 1;
        BigInteger newCardNumber = cardNumber.add(BigInteger.valueOf(one));
        cardNumber = newCardNumber;
        return newCardNumber;
    }
    private static Integer createFirstPassword(){
        Integer firstPassword = 1000;
        Integer newFirstPassword = firstPassword + 1;
        firstPassword = newFirstPassword;
        return  newFirstPassword;
    }
    private static Integer createCvv2(){
        Integer cvv2 = 2000;
        Integer newCvv2 = cvv2 + 1;
        cvv2 = newCvv2;
        return  cvv2;
    }

}
