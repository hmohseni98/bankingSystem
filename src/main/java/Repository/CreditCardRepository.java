package Repository;

import Database.CreateConnection;
import Entity.Account;
import Entity.Branch;
import Entity.CreditCard;
import Entity.Customer;
import Enum.*;
import List.CreditCardList;

import java.sql.*;

public class CreditCardRepository {
    private Connection connection = CreateConnection.connection;

    public CreditCardRepository() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS credit_card( " +
                "id serial primary key, " +
                "account_id int, " +
                "card_number varchar(16), " +
                "first_password int, " +
                "second_password int, " +
                "cvv2 int, " +
                "expire_date DATE, " +
                "CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account(id));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Integer insert(CreditCard creditCard) throws SQLException {
        String insert = "INSERT INTO credit_card (account_id,card_number,first_password,second_password,cvv2,expire_date)" +
                " VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, creditCard.getAccount().getId());
        preparedStatement.setString(2, creditCard.getCardNumber());
        preparedStatement.setInt(3, creditCard.getFirstPassword());
        preparedStatement.setInt(4, creditCard.getSecondPassword());
        preparedStatement.setInt(5, creditCard.getCvv2());
        preparedStatement.setDate(6, creditCard.getExpireDate());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()) {
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }

    public void update(CreditCard creditCard) throws SQLException {
        String update = "UPDATE credit_card " +
                "SET account_id=? , card_number=? , first_password=? , second_password=? , cvv2=? , expire_date=? " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, creditCard.getAccount().getId());
        preparedStatement.setString(2, creditCard.getCardNumber());
        preparedStatement.setInt(3, creditCard.getFirstPassword());
        preparedStatement.setInt(4, creditCard.getSecondPassword());
        preparedStatement.setInt(5, creditCard.getCvv2());
        preparedStatement.setDate(6, creditCard.getExpireDate());
        preparedStatement.setInt(7, creditCard.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete(CreditCard creditCard) throws SQLException {
        String delete = "DELETE FROM credit_card " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, creditCard.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public CreditCardList findAll() throws SQLException {
        String findAll = "SELECT * FROM credit_card " +
                "INNER JOIN account a on a.id = credit_card.account_id " +
                "INNER JOIN customer c on c.id = a.customer_id " +
                "INNER JOIN branch b on b.id = a.branch_id ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        CreditCardList creditCardList = new CreditCardList();
        AccountRepository accountRepository = new AccountRepository();
        while (resultSet.next()) {
            creditCardList.add(new CreditCard(resultSet.getInt("id"),
                    new Account(resultSet.getInt("account_id"),
                            new Branch(resultSet.getInt("branch_id"),
                                    resultSet.getString("name")),
                            new Customer(resultSet.getInt("customer_id"),
                                    resultSet.getString("firstname"),
                                    resultSet.getString("lastname")),
                            resultSet.getInt("amount"),
                            AccountStatus.valueOf(resultSet.getString("accountstatus"))),
                    resultSet.getString("card_number"),
                    resultSet.getInt("first_password"),
                    resultSet.getInt("second_password"),
                    resultSet.getInt("cvv2"),
                    resultSet.getDate("expire_date")));
        }
        return creditCardList;
    }

    public CreditCard findById(Integer id) throws SQLException {
        String findById = "SELECT * FROM credit_card " +
                "INNER JOIN account a on a.id = credit_card.account_id " +
                "INNER JOIN customer c on c.id = a.customer_id " +
                "INNER JOIN branch b on b.id = a.branch_id " +
                "WHERE credit_card.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountRepository accountRepository = new AccountRepository();
        CreditCard creditCard = null;
        if (resultSet.next()) {
            creditCard = new CreditCard(resultSet.getInt("id"),
                    new Account(resultSet.getInt("account_id"),
                            new Branch(resultSet.getInt("branch_id"),
                                    resultSet.getString("name")),
                            new Customer(resultSet.getInt("customer_id"),
                                    resultSet.getString("firstname"),
                                    resultSet.getString("lastname")),
                            resultSet.getInt("amount"),
                            AccountStatus.valueOf(resultSet.getString("accountstatus"))),
                    resultSet.getString("card_number"),
                    resultSet.getInt("first_password"),
                    resultSet.getInt("second_password"),
                    resultSet.getInt("cvv2"),
                    resultSet.getDate("expire_date"));
        }
        return creditCard;
    }

    public CreditCard findByAccountId(Integer account_id) throws SQLException {
        String findById = "SELECT * FROM credit_card " +
                "INNER JOIN account a on a.id = credit_card.account_id " +
                "INNER JOIN customer c on c.id = a.customer_id " +
                "INNER JOIN branch b on b.id = a.branch_id " +
                "WHERE a.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1, account_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountRepository accountRepository = new AccountRepository();
        CreditCard creditCard = null;
        if (resultSet.next()) {
            creditCard = new CreditCard(resultSet.getInt("id"),
                    new Account(resultSet.getInt("account_id"),
                            new Branch(resultSet.getInt("branch_id"),
                                    resultSet.getString("name")),
                            new Customer(resultSet.getInt("customer_id"),
                                    resultSet.getString("firstname"),
                                    resultSet.getString("lastname")),
                            resultSet.getInt("amount"),
                            AccountStatus.valueOf(resultSet.getString("accountstatus"))),
                    resultSet.getString("card_number"),
                    resultSet.getInt("first_password"),
                    resultSet.getInt("second_password"),
                    resultSet.getInt("cvv2"),
                    resultSet.getDate("expire_date"));
        }
        return creditCard;
    }

    public CreditCard findByCardNumber(String cardNumber) throws SQLException {
        String findById = "SELECT * FROM credit_card " +
                "INNER JOIN account a on a.id = credit_card.account_id " +
                "INNER JOIN customer c on c.id = a.customer_id " +
                "INNER JOIN branch b on b.id = a.branch_id " +
                "WHERE card_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setString(1, cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountRepository accountRepository = new AccountRepository();
        CreditCard creditCard = null;
        if (resultSet.next()) {
            creditCard = new CreditCard(resultSet.getInt("id"),
                    new Account(resultSet.getInt("account_id"),
                            new Branch(resultSet.getInt("branch_id"),
                                    resultSet.getString("name")),
                            new Customer(resultSet.getInt("customer_id"),
                                    resultSet.getString("firstname"),
                                    resultSet.getString("lastname")),
                            resultSet.getInt("amount"),
                            AccountStatus.valueOf(resultSet.getString("accountstatus"))),
                    resultSet.getString("card_number"),
                    resultSet.getInt("first_password"),
                    resultSet.getInt("second_password"),
                    resultSet.getInt("cvv2"),
                    resultSet.getDate("expire_date"));
        }
        return creditCard;
    }
}
