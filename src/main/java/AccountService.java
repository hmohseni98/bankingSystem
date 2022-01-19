import java.sql.SQLException;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    private BranchService branchService = new BranchService();
    private CustomerService customerService = new CustomerService();

    public AccountService() throws SQLException {
    }
    public void insert(Integer amount,Integer branch_id,Integer customer_id) throws SQLException {
        Branch branch;
        branch = branchService.findById(branch_id);
        if (branch == null) {
            System.out.println("branch does not exist!");
            return;
        }
        Customer customer;
        customer = customerService.findById(customer_id);
        if (customer == null) {
            System.out.println("customer does not exist!");
            return;
        }
        Account account;
        account = new Account(null,branch,customer,amount,AccountStatus.active);
        accountRepository.insert(account);
    }
    public void update(Integer account_id,Integer amount) throws SQLException {
        Account account;
        account = accountRepository.findById(account_id);
        account.setAmount(amount);
        accountRepository.update(account);
    }
    public void delete(Integer account_id) throws SQLException {
        Account account;
        account = accountRepository.findById(account_id);
        accountRepository.delete(account);
    }
    public AccountList findAll() throws SQLException {
        AccountList accountList;
        accountList =accountRepository.findAll();
        return  accountList;
    }
    public AccountList findAllByCustomerId(Integer customer_id) throws SQLException{
        AccountList accountList;
        accountList = accountRepository.findAllByCustomerId(customer_id);
        return accountList;
    }
    public Account findById(Integer account_id) throws SQLException{
        Account account;
        account = accountRepository.findById(account_id);
        return account;
    }
    public Boolean checkBalance(Integer account_id,Integer amount) throws SQLException{
        Account account;
        account = accountRepository.findById(account_id);
        if (account.getAmount() < amount + 600 ){
            System.out.println("not enough money!");
            return false;
        }
        return true;
    }
}
