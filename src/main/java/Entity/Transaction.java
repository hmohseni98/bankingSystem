package Entity;

import java.sql.Date;
import Enum.*;

public class Transaction {
    private Integer id;
    private Account account;
    private Integer amount;
    private TransactionStatus status;
    private TransactionType type;
    private Date date;


    public Transaction() {
    }

    public Transaction(Integer id, Account account, Integer amount, TransactionStatus status, TransactionType type, Date date) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.date = date;
    }
    public Transaction(Integer id, Account account, Integer amount, TransactionStatus status, TransactionType type) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
