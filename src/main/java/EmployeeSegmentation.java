public class EmployeeSegmentation {
    private Integer id;
    private Employee employee;
    private Branch branch;
    private Employee manager;
    private Department departmentName;

    public EmployeeSegmentation(Integer id, Employee employee, Branch branch, Employee manager, Department departmentName) {
        this.id = id;
        this.employee = employee;
        this.branch = branch;
        this.manager = manager;
        this.departmentName = departmentName;
    }

    public EmployeeSegmentation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Department departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
