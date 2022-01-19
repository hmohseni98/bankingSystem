import java.sql.Date;
import java.sql.SQLException;

public class FakeDB {
    public void fillData() throws SQLException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee(null,"hassan","mohseni");
        employee.setId(employeeRepository.insert(employee));
        Employee employee1 = new Employee(null,"javad","karimi");
        employee1.setId(employeeRepository.insert(employee1));
        Employee employee2 = new Employee(null,"mahdi","rezaee");
        employeeRepository.insert(employee2);
        employee2.setId(employeeRepository.insert(employee2));
        Employee employee3 = new Employee(null,"sadra","jafari");
        employeeRepository.insert(employee3);
        employee3.setId(employeeRepository.insert(employee3));

        Employee employee4 = new Employee(null,"zahra","mohseni");
        employeeRepository.insert(employee4);
        employee4.setId(employeeRepository.insert(employee4));
        Employee employee5 = new Employee(null,"maohammad","karimi");
        employeeRepository.insert(employee5);
        employee5.setId(employeeRepository.insert(employee5));
        Employee employee6 = new Employee(null,"sara","rezaee");
        employeeRepository.insert(employee6);
        employee6.setId(employeeRepository.insert(employee6));
        Employee employee7 = new Employee(null,"masuod","jafari");
        employeeRepository.insert(employee7);
        employee7.setId(employeeRepository.insert(employee7));

        BranchRepository branchRepository = new BranchRepository();
        Branch branch1 = new Branch(null,"jami");
        branch1.setId(branchRepository.insert(branch1));
        Branch branch2 = new Branch(null,"ferdosi");
        branchRepository.insert(branch2);
        branch2.setId(branchRepository.insert(branch2));

        EmployeeSegmentationRepository employeeSegmentationRepository = new EmployeeSegmentationRepository();
        EmployeeSegmentation employeeSegmentation1 =
                new EmployeeSegmentation(null,employee1,branch1,employee3,Department.financialManager);
        employeeSegmentationRepository.insert(employeeSegmentation1);
        EmployeeSegmentation employeeSegmentation2 =
                new EmployeeSegmentation(null,employee1,branch1,employee,Department.financial);
        employeeSegmentationRepository.insert(employeeSegmentation2);
        EmployeeSegmentation employeeSegmentation3 =
                new EmployeeSegmentation(null,employee2,branch1,employee,Department.financial);
        employeeSegmentationRepository.insert(employeeSegmentation3);
        EmployeeSegmentation employeeSegmentation4 =
                new EmployeeSegmentation(null,employee3,branch1,employee3,Department.branchManager);
        employeeSegmentationRepository.insert(employeeSegmentation4);

        EmployeeSegmentation employeeSegmentation5 =
                new EmployeeSegmentation(null,employee4,branch2,employee7,Department.commercialManager);
        employeeSegmentationRepository.insert(employeeSegmentation5);
        EmployeeSegmentation employeeSegmentation6 =
                new EmployeeSegmentation(null,employee5,branch2,employee4,Department.commercial);
        employeeSegmentationRepository.insert(employeeSegmentation6);
        EmployeeSegmentation employeeSegmentation7 =
                new EmployeeSegmentation(null,employee6,branch2,employee4,Department.commercialManager);
        employeeSegmentationRepository.insert(employeeSegmentation7);
        EmployeeSegmentation employeeSegmentation8 =
                new EmployeeSegmentation(null,employee7,branch2,employee7,Department.branchManager);
        employeeSegmentationRepository.insert(employeeSegmentation8);

        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer1 = new Customer(null,"zeynab","sabori");
        customer1.setId(customerRepository.insert(customer1));
        Customer customer2 = new Customer(null,"mehdi","yaghobi");
        customer2.setId(customerRepository.insert(customer2));
        Customer customer3 = new Customer(null,"zahra","younesi");
        customer3.setId(customerRepository.insert(customer3));
        Customer customer4 = new Customer(null,"hamid","mohseni");
        customer4.setId(customerRepository.insert(customer4));


        AccountRepository accountRepository = new AccountRepository();
        Account account1 = new Account(null,branch1,customer1,100000,AccountStatus.active);
        account1.setId(accountRepository.insert(account1));
        Account account2 = new Account(null,branch2,customer1,150000,AccountStatus.active);
        account2.setId(accountRepository.insert(account2));
        Account account3 = new Account(null,branch1,customer2,100000,AccountStatus.active);
        account3.setId(accountRepository.insert(account3));
        Account account4 = new Account(null,branch2,customer3,200000,AccountStatus.active);
        account4.setId(accountRepository.insert(account4));
        Account account5 = new Account(null,branch2,customer4,200000,AccountStatus.active);
        account5.setId(accountRepository.insert(account5));

        CreditCardRepository creditCardRepository = new CreditCardRepository();
        CreditCard creditCard1 = new CreditCard(null,account1,"6037991110124229",1025,-1,1235, Date.valueOf("2026-10-10"));
        creditCard1.setId(creditCardRepository.insert(creditCard1));
        CreditCard creditCard2 = new CreditCard(null,account2,"6037991110124230",1026,-1,1236, Date.valueOf("2026-10-10"));
        creditCard2.setId(creditCardRepository.insert(creditCard2));
        CreditCard creditCard3 = new CreditCard(null,account3,"6037991110124231",1027,-1,1237, Date.valueOf("2026-10-10"));
        creditCard3.setId(creditCardRepository.insert(creditCard3));
        CreditCard creditCard4 = new CreditCard(null,account4,"6037991110124232",1028,-1,1238, Date.valueOf("2026-10-10"));
        creditCard4.setId(creditCardRepository.insert(creditCard4));
        CreditCard creditCard5 = new CreditCard(null,account5,"6037991110124233",1029,-1,1239, Date.valueOf("2026-10-10"));
        creditCard5.setId(creditCardRepository.insert(creditCard5));

        TransactionRepository transactionRepository = new TransactionRepository();
        Transaction transaction1 = new Transaction
                (null,account1,15000,TransactionStatus.accepted,TransactionType.withdraw,Date.valueOf("2022-01-10"));
        transaction1.setId(transactionRepository.insert(transaction1));
        Transaction transaction2 = new Transaction
                (null,account1,20000,TransactionStatus.accepted,TransactionType.deposit,Date.valueOf("2022-01-11"));
        transaction2.setId(transactionRepository.insert(transaction2));
        Transaction transaction3 = new Transaction
                (null,account2,40000,TransactionStatus.accepted,TransactionType.deposit,Date.valueOf("2022-01-11"));
        transaction3.setId(transactionRepository.insert(transaction3));
        Transaction transaction4 = new Transaction
                (null,account3,180000,TransactionStatus.accepted,TransactionType.deposit,Date.valueOf("2022-01-12"));
        transaction4.setId(transactionRepository.insert(transaction4));
        Transaction transaction5 = new Transaction
                (null,account3,75000,TransactionStatus.accepted,TransactionType.withdraw,Date.valueOf("2022-01-11"));
        transaction5.setId(transactionRepository.insert(transaction5));
        Transaction transaction6 = new Transaction
                (null,account4,14500,TransactionStatus.accepted,TransactionType.withdraw,Date.valueOf("2022-01-12"));
        transaction6.setId(transactionRepository.insert(transaction6));
        Transaction transaction7 = new Transaction
                (null,account5,178000,TransactionStatus.accepted,TransactionType.withdraw,Date.valueOf("2022-01-11"));
        transaction7.setId(transactionRepository.insert(transaction7));
        Transaction transaction8 = new Transaction
                (null,account5,15000,TransactionStatus.accepted,TransactionType.deposit,Date.valueOf("2022-01-10"));
        transaction8.setId(transactionRepository.insert(transaction8));

    }
}
