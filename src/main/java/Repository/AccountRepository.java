package Repository;

import Database.*;
import Entity.Account;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    private Connection connection = CreateConnection.connection;

    public AccountRepository() {
    }

    public Integer insert(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                return account.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Integer delete(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(account);
                transaction.commit();
                return account.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Integer update(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(account);
                transaction.commit();
                return account.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        var session = sessionFactory.openSession();
        String hql = "FROM Entity.Account";
        var query = session.createQuery(hql, Account.class);
        query.getResultStream().forEach(account -> accountList.add(account));
        return accountList;
    }

    public Account findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Account.class, id);
        }
    }

    public List<Account> findAllByCustomerId(Integer id) {
        List<Account> accountList = new ArrayList<>();
        var session = sessionFactory.openSession();
        String sql = "SELECT * FROM Account a " +
                "INNER JOIN branch b " +
                "ON a.branch_id = b.id " +
                "INNER JOIN customer c " +
                "ON a.customer_id = c.id " +
                "WHERE c.id =:id";
        var query = session.createNativeQuery(sql, Account.class);
        query.setParameter("id", id);
        query.getResultStream().forEach(account -> accountList.add(account));
        return accountList;
    }
}
