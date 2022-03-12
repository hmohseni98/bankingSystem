import Entity.Account;
import Entity.Branch;
import Entity.Customer;
import Enum.AccountStatus;
import Database.CreateConnection;
import Repository.AccountRepository;
import Repository.BranchRepository;
import Repository.CustomerRepository;
import org.junit.jupiter.api.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private AccountRepository accountRepository;
    private Account account;
    private Branch branch;
    private Branch branch1;
    private Customer customer;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() throws SQLException {

        CustomerRepository customerRepository = new CustomerRepository();
        BranchRepository branchRepository = new BranchRepository();
        accountRepository = new AccountRepository();
        customer = new Customer(null, "hassan", "mohseni");
        branch = new Branch(null, "saadi");
        branch1 = new Branch(null, "ferdowsi");


        customer.setId(customerRepository.insert(customer));
        branch.setId(branchRepository.insert(branch));
        branch1.setId(branchRepository.insert(branch1));


    }


    @Test
    public void connectionTest() {
        assertDoesNotThrow(() -> CreateConnection.connection);
    }

    @Test
    public void saveTest() {
        //arrange
        account = new Account(null, branch, customer, 40000, AccountStatus.active);

        //act
        accountRepository.insert(account);

        //assert
        Account loadedAccount = accountRepository.findById(account.getId());

        assertNotNull(loadedAccount);
        assertEquals(40000, account.getAmount());
    }

    @Test
    public void updateTest() {
        //arrange
        account = new Account(null, branch, customer, 40000, AccountStatus.active);
        account.setId(accountRepository.insert(account));

        //act
        account.setAmount(50000);
        accountRepository.update(account);

        //assert
        Account loadedAccount = accountRepository.findById(account.getId());
        assertEquals(50000, loadedAccount.getAmount());
    }

    @Test
    public void deleteTest() {
        //arrange
        account = new Account(null, branch, customer, 40000, AccountStatus.active);
        Integer accountId = accountRepository.insert(account);

        //act

        accountRepository.delete(account);

        //assert
        Account loadedAccount = accountRepository.findById(accountId);
        assertNull(loadedAccount);
    }


    @Test
    public void findByIdTest() {
        //arrange
        account = new Account(null, branch, customer, 40000, AccountStatus.active);

        //act
        accountRepository.insert(account);
        accountRepository.findById(account.getId());
        //assert
        List<Account> accountList = accountRepository.findAll();
        assertAll(
                () -> assertEquals(account.getId(), accountList.get(0).getId()),
                () -> assertEquals(account.getAmount(), accountList.get(0).getAmount()),
                () -> assertEquals(account.getCustomer().getId(), accountList.get(0).getCustomer().getId())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        account = new Account(null, branch, customer, 40000, AccountStatus.active);
        Account account1 = new Account(null, branch1, customer, 20000, AccountStatus.active);


        //act
        accountRepository.insert(account);
        accountRepository.insert(account1);

        //assert
        assertEquals(2, accountRepository.findAll().size());
    }

    @Test
    public void findAllByCustomerIdTest() {
        //arrange
        account = new Account(null, branch, customer, 40000, AccountStatus.active);
        Account account1 = new Account(null, branch1, customer, 20000, AccountStatus.active);
        accountRepository.insert(account);
        accountRepository.insert(account1);

        //act
        List<Account> accountList = accountRepository.findAllByCustomerId(customer.getId());

        //assert
        assertEquals(2, accountList.size());

    }

    @AfterEach
    public void afterEach() throws SQLException {
        String[] strings = new String[]{
                "delete from account",
                "delete from branch",
                "delete from customer"
        };
        for (String s : strings) {
            PreparedStatement preparedStatement = CreateConnection.connection.prepareStatement(s);
            preparedStatement.execute();
            preparedStatement.close();
        }
    }

    @AfterAll
    public static void afterAll() {

    }
}