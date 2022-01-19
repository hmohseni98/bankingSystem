import java.sql.Date;

public class CreditCard {
    private Integer id;
    private Account account;
    private String cardNumber;
    private Integer firstPassword;
    private Integer secondPassword;
    private Integer cvv2;
    private Date expireDate;

    public CreditCard(Integer id, Account account, String cardNumber, Integer firstPassword, Integer secondPassword, Integer cvv2, Date expireDate) {
        this.id = id;
        this.account = account;
        this.cardNumber = cardNumber;
        this.firstPassword = firstPassword;
        this.secondPassword = secondPassword;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
    }

    public CreditCard() {
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(Integer firstPassword) {
        this.firstPassword = firstPassword;
    }

    public Integer getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(Integer secondPassword) {
        this.secondPassword = secondPassword;
    }

    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", account=" + account +
                ", cardNumber='" + cardNumber + '\'' +
                ", firstPassword=" + firstPassword +
                ", secondPassword=" + secondPassword +
                ", cvv2=" + cvv2 +
                ", expireDate=" + expireDate +
                '}';
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
