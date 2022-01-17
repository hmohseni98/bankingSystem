public class CustomerBranch {
    private Customer customer;
    private Branch branch;

    public CustomerBranch(Customer customer, Branch branch) {
        this.customer = customer;
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
