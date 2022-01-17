public class Account {
    private Integer id;
    private Branch branch;
    private Customer customer;
    private Integer accountNumber;
    private Integer amount;
    private Boolean active_status;

    public Account(Integer id, Branch branch, Customer customer, Integer accountNumber, Integer amount, Boolean active_status) {
        this.id = id;
        this.branch = branch;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.active_status = active_status;
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

    public Boolean getActive_status() {
        return active_status;
    }

    public void setActive_status(Boolean active_status) {
        this.active_status = active_status;
    }
}
