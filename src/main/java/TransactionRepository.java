import java.sql.*;

public class TransactionRepository {
    Connection connection = CreateConnection.connection;

    public TransactionRepository() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS transaction( " +
                "id serial primary key, " +
                "account_id int, " +
                "amount int, " +
                "status transactionStatus, " +
                "type transactionType, " +
                "CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account(id));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void insert(Transaction transaction) throws SQLException {
        String insert = "INSERT INTO transaction (account_id,amount,status,type)" +
                " VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, transaction.getAccount().getId());
        preparedStatement.setInt(2, transaction.getAmount());
        preparedStatement.setString(3,transaction.getStatus().name());
        preparedStatement.setString(4, transaction.getType().name());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public TransactionList findAll() throws SQLException{
        String findAll = "SELECT * FROM transaction ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        TransactionList transactionList = new TransactionList();
        AccountRepository accountRepository = new AccountRepository();
        while(resultSet.next()){
            transactionList.add(new Transaction(null,
                    accountRepository.findById(resultSet.getInt("account_id")),
                    resultSet.getInt("amount"),
                    TransactionStatus.valueOf(resultSet.getString("status")),
                    TransactionType.valueOf(resultSet.getString("type"))));
        }
        return transactionList;
    }
    public Transaction findById(Integer id) throws SQLException{
        String findById = "SELECT * FROM transaction " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountRepository accountRepository = new AccountRepository();
        Transaction transaction = null;
        while(resultSet.next()){
            transaction = new Transaction(null,
                    accountRepository.findById(resultSet.getInt("account_id")),
                    resultSet.getInt("amount"),
                    TransactionStatus.valueOf(resultSet.getString("status")),
                    TransactionType.valueOf(resultSet.getString("type")));
        }
        return transaction;
    }
}
