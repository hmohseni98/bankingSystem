package Service;

import Entity.Branch;
import List.BranchList;
import Repository.BranchRepository;

import java.sql.SQLException;

public class BranchService {
    private BranchRepository branchRepository = new BranchRepository();

    public BranchService() throws SQLException {
    }
    public Branch findById(Integer id) throws SQLException {
        Branch branch;
        branch = branchRepository.findById(id);
        return branch;
    }
    public BranchList findAll() throws SQLException{
        return branchRepository.findAll();
    }
}
