package Service;

import Entity.Account;
import Entity.CreditCard;
import List.CreditCardList;
import Repository.CreditCardRepository;
import Enum.*;
import CustomException.*;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static Enum.AccountStatus.active;

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
            throw new InvalidSourceCard();
        }
        CreditCard destination;
        destination = creditCardRepository.findByCardNumber(dstCard);
        if (destination == null){
            throw new InvalidDestinationCard();
        }
        if (!accountService.checkBalance(source.getAccount().getId(),amount)){
            throw new InsufficientMoney();
        }
        return true;
    }
    public Boolean checkTransfer2(String srcCard,Integer secondPassword,Integer cvv2,Date exDate) throws SQLException {
        CreditCard source;
        source = creditCardRepository.findByCardNumber(srcCard);
        if (source.getSecondPassword().equals(-1)){
            throw new SecondPasswordNotDefine();
        }
        if (!source.getSecondPassword().equals(secondPassword)){
            throw new SecondPasswordNotCorrect();
        }
        if (!source.getCvv2().equals(cvv2)){
            throw new Cvv2NotCorrect();
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
    public String checkCard(String cardNumber,Integer firstPassword) throws SQLException {
        CreditCard creditCard = null;
        creditCard = creditCardRepository.findByCardNumber(cardNumber);
        if (creditCard == null)
            return "null";
        if (creditCard.getAccount().getAccountStatus() == AccountStatus.deactive){
            return "deactive";
        }
        if (creditCard.getFirstPassword().equals(firstPassword))
            return "true";
        return "false";
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
