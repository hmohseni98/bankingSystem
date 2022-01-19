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
        creditCard = new CreditCard(null,account,cardNumber,firstPassword,null,cvv2,Date.valueOf("2026-10-10"));
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
    public Boolean checkCardNumber(String srcCard, String dstCard, Integer amount) throws SQLException{
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
        return true;
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
