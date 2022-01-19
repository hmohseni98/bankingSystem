import java.sql.*;

public class AccountRepository {

    private Connection connection = CreateConnection.connection;

    public AccountRepository() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS account( " +
                "id serial primary key, " +
                "branch_id int, " +
                "customer_id int, " +
                "amount int, " +
                "active_status account_status, " +
                "CONSTRAINT fk_branch FOREIGN KEY (branch_id) REFERENCES branch(id), " +
                "CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Integer insert(Account account) throws SQLException {
        String insert = "INSERT INTO account (branch_id,customer_id, " +
                "amount,active_status) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, account.getBranch().getId());
        preparedStatement.setInt(2, account.getCustomer().getId());
        preparedStatement.setInt(3, account.getAmount());
        preparedStatement.setObject(4,account.getStatus().name(), Types.OTHER);
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()){
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }

    public void update(Account account) throws SQLException {
        String update = "UPDATE account " +
                "SET branch_id=? , customer_id=?, amount=? , active_status=? " +
                "WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, account.getBranch().getId());
        preparedStatement.setInt(2, account.getCustomer().getId());
        preparedStatement.setInt(3, account.getAmount());
        preparedStatement.setString(4, account.getStatus().name());
        preparedStatement.setInt(5, account.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete(Account account) throws SQLException {
        String delete = "DELETE FROM accout " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, account.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public AccountList findAll() throws SQLException {
        String findAll = "SELECT * FROM account a " +
                "INNER JOIN branch b " +
                "ON a.branch_id = b.id " +
                "INNER JOIN customer c " +
                "ON a.customer_id = c.id ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountList accountList = new AccountList();
        while (resultSet.next()) {
            accountList.add(new Account(resultSet.getInt("id"),
                    new Branch(resultSet.getInt("branch_id"),
                            resultSet.getString("name")),
                    new Customer(resultSet.getInt("customer_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")),
                    resultSet.getInt("amount"),
                    AccountStatus.valueOf(resultSet.getString("status"))));
        }
        return accountList;
    }

    public Account findById(Integer id) throws SQLException {
        String findById = "SELECT * FROM account a " +
                "INNER JOIN branch b " +
                "ON a.branch_id = b.id " +
                "INNER JOIN customer c " +
                "ON a.customer_id = c.id " +
                "WHERE a.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account1 = null;
        if (resultSet.next()) {
            account1 = new Account(resultSet.getInt("id"),
                    new Branch(resultSet.getInt("branch_id"),
                            resultSet.getString("name")),
                    new Customer(resultSet.getInt("customer_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")),
                    resultSet.getInt("amount"),
                    AccountStatus.valueOf(resultSet.getString("status")));
            preparedStatement.close();
        }
        return account1;
    }
    public AccountList findAllByCustomerId(Integer id) throws SQLException {
        String findAllByCustomerId = "SELECT * FROM account a " +
                "INNER JOIN branch b " +
                "ON a.branch_id = b.id " +
                "INNER JOIN customer c " +
                "ON a.customer_id = c.id " +
                "WHERE c.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findAllByCustomerId);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountList accountList = new AccountList();
        while (resultSet.next()) {
            accountList.add(new Account(resultSet.getInt("id"),
                    new Branch(resultSet.getInt("branch_id"),
                            resultSet.getString("name")),
                    new Customer(resultSet.getInt("customer_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")),
                    resultSet.getInt("amount"),
                    AccountStatus.valueOf(resultSet.getString("status"))));
        }
        return accountList;
    }

}
