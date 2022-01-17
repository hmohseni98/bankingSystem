public class Employee {
    private Integer id;
    private Branch branch;
    private String firstName;
    private String lastName;
    private Contract contractType;
    private Post post;

    public Employee(Integer id, Branch branch, String firstName, String lastName, Contract contractType, Post post) {
        this.id = id;
        this.branch = branch;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contractType = contractType;
        this.post = post;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contract getContractType() {
        return contractType;
    }

    public void setContractType(Contract contractType) {
        this.contractType = contractType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
