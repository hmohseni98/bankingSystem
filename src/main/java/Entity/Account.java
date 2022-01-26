package Entity;
import Enum.*;
public class Account {
    private Integer id;
    private Branch branch;
    private Customer customer;
    private Integer amount;
    private AccountStatus accountStatus;

    public Account(Integer id, Branch branch, Customer customer, Integer amount, AccountStatus accountStatus) {
        this.id = id;
        this.branch = branch;
        this.customer = customer;
        this.amount = amount;
        this.accountStatus = accountStatus;
    }
    public Account(Branch branch, Customer customer, Integer amount, AccountStatus accountStatus) {
        this.branch = branch;
        this.customer = customer;
        this.amount = amount;
        this.accountStatus = accountStatus;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public AccountStatus getStatus() {
        return accountStatus;
    }

    public void setStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
