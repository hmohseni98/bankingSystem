package Service;

import Entity.Customer;
import Repository.CustomerRepository;

import java.sql.SQLException;

public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public CustomerService() throws SQLException {
    }
    public Integer insert(Customer customer) throws SQLException{
        return customerRepository.insert(customer);
    }
    public Customer findById(Integer id) throws SQLException {
        Customer customer;
        customer = customerRepository.findById(id);
        return customer;
    }
}
