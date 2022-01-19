import java.sql.*;

public class EmployeeSegmentationRepository {
    private Connection connection = CreateConnection.connection;

    public EmployeeSegmentationRepository() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS employee_segmentation( " +
                "id serial primary key, " +
                "employee_id int, " +
                "branch_id int, " +
                "manager_id int " +
                "department_name varchar(20), " +
                "CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id) " +
                "CONSTRAINT fk_branch FOREIGN KEY (branch_id) REFERENCES branch(id) " +
                "CONSTRAINT fk_employee_manager FOREIGN KEY (manager_id) REFERENCES employee(id));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void insert (EmployeeSegmentation employeeSegmentation) throws SQLException{
        String insert = "INSERT INTO employee_segmentation (employee_id,branch_id,manager_id,department_name) " +
                "VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, employeeSegmentation.getEmployee().getId());
        preparedStatement.setInt(2, employeeSegmentation.getBranch().getId());
        preparedStatement.setInt(3, employeeSegmentation.getEmployee().getId());
        preparedStatement.setString(4, employeeSegmentation.getDepartmentName().name());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void update (EmployeeSegmentation employeeSegmentation) throws SQLException{
        String insert = "UPDATE employee_segmentation " +
                "SET employee_id = ? , branch_id = ? , manager_id = ? , department_name = ? " +
                "WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, employeeSegmentation.getEmployee().getId());
        preparedStatement.setInt(2, employeeSegmentation.getBranch().getId());
        preparedStatement.setInt(3, employeeSegmentation.getEmployee().getId());
        preparedStatement.setString(4, employeeSegmentation.getDepartmentName().name());
        preparedStatement.setInt(5, employeeSegmentation.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void delete(EmployeeSegmentation employeeSegmentation) throws SQLException {
        String delete = "DELETE FROM employee_segmentation " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, employeeSegmentation.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public EmployeeSegmentationList findAll() throws SQLException{
        String findAll = "SELECT * FROM employee_segmentation ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        EmployeeSegmentationList employeeSegmentationList = new EmployeeSegmentationList();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        BranchRepository branchRepository = new BranchRepository();
        while (resultSet.next()) {
            employeeSegmentationList.add(new EmployeeSegmentation(resultSet.getInt("id"),
                    employeeRepository.findById(resultSet.getInt("employee_id")),
                    branchRepository.findById(resultSet.getInt("branch_id")),
                    employeeRepository.findById(resultSet.getInt("manager_id")),
                    Department.valueOf(resultSet.getString("department_name"))));
        }
        return employeeSegmentationList;
    }
    public EmployeeSegmentation findById(Integer id) throws SQLException{
        String findById = "SELECT * FROM employee_segmentation " +
                "WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        EmployeeSegmentationList employeeSegmentationList = new EmployeeSegmentationList();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        BranchRepository branchRepository = new BranchRepository();
        EmployeeSegmentation employeeSegmentation = null;
        if (resultSet.next()) {
            employeeSegmentation = new EmployeeSegmentation(resultSet.getInt("id"),
                    employeeRepository.findById(resultSet.getInt("employee_id")),
                    branchRepository.findById(resultSet.getInt("branch_id")),
                    employeeRepository.findById(resultSet.getInt("manager_id")),
                    Department.valueOf(resultSet.getString("department_name")));
        }
        return employeeSegmentation;
    }
}
