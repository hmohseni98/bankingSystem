import java.sql.*;

public class CustomerRepository {

    private Connection connection = CreateConnection.connection;

    public CustomerRepository() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS customer( " +
                "id serial primary key, " +
                "first_name varchar(30), " +
                "last_name varchar(50));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public Integer insert(Customer customer) throws SQLException {
        String insert = "INSERT INTO customer (first_name,last_name) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()){
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }
    public Customer findById(Integer id) throws SQLException{
        String findById = "SELECT * FROM customer " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet =preparedStatement.executeQuery();
        Customer customer = null;
        while (resultSet.next()){
            customer = new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
        }
        return customer;
    }
}
