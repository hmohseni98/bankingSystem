import java.sql.SQLException;

public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public CustomerService() throws SQLException {
    }
    public Customer findById(Integer id) throws SQLException {
        Customer customer;
        customer = customerRepository.findById(id);
        return customer;
    }
}
