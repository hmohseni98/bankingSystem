package Repository;

import Database.CreateConnection;
import Database.SessionFactorySingleton;
import Entity.Customer;
import org.hibernate.SessionFactory;

import java.sql.*;

public class CustomerRepository {

    private Connection connection = CreateConnection.connection;

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public CustomerRepository() {
    }

    public Integer insertHibernate(Customer customer){
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(customer);
                transaction.commit();
                return customer.getId();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }
    public Integer insert(Customer customer) throws SQLException {
        String insert = "INSERT INTO customer (firstname,lastname) VALUES (?,?)";
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
    public Customer findById(Integer id) throws SQLException {
        String findById = "SELECT * FROM customer " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        while (resultSet.next()) {
            customer = new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
        }
        return customer;
    }
}
