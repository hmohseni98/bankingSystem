public class Account {
    private Integer id;
    private Branch branch;
    private Customer customer;
    private Integer accountNumber;
    private Integer amount;
    private Status status;

    public Account(Integer id, Branch branch, Customer customer, Integer accountNumber, Integer amount, Status status) {
        this.id = id;
        this.branch = branch;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
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

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
