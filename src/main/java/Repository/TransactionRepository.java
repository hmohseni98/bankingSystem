package Repository;

import Database.CreateConnection;
import Entity.Transaction;
import List.TransactionList;
import Enum.*;

import java.sql.*;
import java.time.LocalDate;

public class TransactionRepository {
    Connection connection = CreateConnection.connection;

    public TransactionRepository() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS transaction( " +
                "id serial primary key, " +
                "account_id int, " +
                "amount int, " +
                "status transaction_status, " +
                "type transaction_type, " +
                "date Date, " +
                "CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account(id));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public Integer insert(Transaction transaction) throws SQLException {
        String insert = "INSERT INTO transaction (account_id,amount,status,type,date)" +
                " VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, transaction.getAccount().getId());
        preparedStatement.setInt(2, transaction.getAmount());
        preparedStatement.setObject(3,transaction.getStatus().name(), Types.OTHER);
        preparedStatement.setObject(4, transaction.getType().name(),Types.OTHER);
        preparedStatement.setDate(5,transaction.getDate());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()){
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }
    public TransactionList findAllById(Date startDate, Integer account_id) throws SQLException{
        String findAll = "SELECT * FROM transaction " +
                " WHERE date BETWEEN ? AND ? AND account_id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        preparedStatement.setDate(1,startDate);
        preparedStatement.setDate(2,Date.valueOf(LocalDate.now()));
        preparedStatement.setInt(3,account_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        TransactionList transactionList = new TransactionList();
        AccountRepository accountRepository = new AccountRepository();
        while(resultSet.next()){
            transactionList.add(new Transaction(resultSet.getInt("id"),
                    accountRepository.findById(resultSet.getInt("account_id")),
                    resultSet.getInt("amount"),
                    TransactionStatus.valueOf(resultSet.getString("status")),
                    TransactionType.valueOf(resultSet.getString("type")),
                    resultSet.getDate("date")));
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
            transaction = new Transaction(resultSet.getInt("id"),
                    accountRepository.findById(resultSet.getInt("account_id")),
                    resultSet.getInt("amount"),
                    TransactionStatus.valueOf(resultSet.getString("status")),
                    TransactionType.valueOf(resultSet.getString("type")),
                    resultSet.getDate("date"));
        }
        return transaction;
    }
}
