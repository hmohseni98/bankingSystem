package Repository;

import Database.CreateConnection;
import Database.SessionFactorySingleton;
import Entity.Branch;
import List.BranchList;
import org.hibernate.SessionFactory;

import java.sql.*;

public class BranchRepository {
    private Connection connection = CreateConnection.connection;
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public BranchRepository() {
    }

    public Integer insertHibernate(Branch branch) {
        try (var session = sessionFactory.openSession()){
            var transaction  = session.beginTransaction();
            try{
                session.save(branch);
                transaction.commit();
                return branch.getId();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }
    public Integer insert(Branch branch) throws SQLException {
        String insert = "INSERT INTO branch (name) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, branch.getName());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()) {
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }

    public void update(Branch branch) throws SQLException {
        String update = "UPDATE branch " +
                "SET name = ?" +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, branch.getName());
        preparedStatement.setInt(2, branch.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete(Branch branch) throws SQLException {
        String delete = "DELETE FROM branch " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, branch.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public BranchList findAll() throws SQLException {
        String findAll = "SELECT * FROM branch ";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        BranchList branchList = new BranchList();
        while (resultSet.next()) {
            branchList.add(new Branch(resultSet.getInt("id"),
                    resultSet.getString("name")));
        }
        return branchList;
    }

    public Branch findById(Integer id) throws SQLException {
        String findById = "SELECT * FROM branch " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Branch branch = null;
        if (resultSet.next()) {
            branch = new Branch(resultSet.getInt("id"),
                    resultSet.getString("name"));
        }
        return branch;
    }

}
