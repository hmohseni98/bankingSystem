import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionService {
    private TransactionRepository transactionRepository = new TransactionRepository();

    public TransactionService() throws SQLException {
    }

    public TransactionList findAllById(Date startDate,String cardNumber) throws SQLException {
        CreditCardService creditCardService = new CreditCardService();
        Integer account_id = creditCardService.findByCardNumber(cardNumber);
        TransactionList transactionList = new TransactionList();
        transactionList = transactionRepository.findAllById(startDate,account_id);
        return transactionList;
    }
    public void withdrawTransaction(Account account,Integer amount) throws SQLException {
        Transaction transaction = new Transaction(null,account,amount,TransactionStatus.accepted,TransactionType.withdraw,Date.valueOf(LocalDate.now()));
        transactionRepository.insert(transaction);
    }
    public void depositTransaction(Account account,Integer amount) throws SQLException {
        Transaction transaction = new Transaction(null,account,amount,TransactionStatus.accepted,TransactionType.deposit, Date.valueOf(LocalDate.now()));
        transactionRepository.insert(transaction);
    }
}
