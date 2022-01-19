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
}
