import java.sql.*;

public class EmployeeRepository {

    private Connection connection = CreateConnection.connection;

    public EmployeeRepository() throws SQLException{
        String createTable = "CREATE TABLE IF NOT EXISTS employee( " +
                "id serial primary key, " +
                "first_name varchar(30), " +
                "last_name varchar(50));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void insert(Employee employee) throws SQLException {
        String insert = "INSERT INTO employee (first_name,last_name) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void update(Employee employee) throws SQLException {
        String update = "UPDATE employee " +
                "SET first_name = ? , last_name = ?" +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, employee.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void delete(Employee employee) throws SQLException {
        String delete = "DELETE FROM employee " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public EmployeeList findAll() throws SQLException{
        String findAll = "SELECT * FROM employee ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet =preparedStatement.executeQuery();
        EmployeeList employeeList = new EmployeeList();
        while (resultSet.next()){
            employeeList.add(new Employee(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")));
        }
        return employeeList;
    }
    public Employee findById(Integer id) throws SQLException{
        String findById = "SELECT * FROM employee " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet =preparedStatement.executeQuery();
        Employee employee = null;
        while (resultSet.next()){
            employee = new Employee(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
        }
        return employee;
    }
}
