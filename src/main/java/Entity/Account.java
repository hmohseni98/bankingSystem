package Entity;

import Enum.*;
import Entity.*;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Customer customer;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Account(Branch branch, Customer customer, Integer amount, AccountStatus accountStatus) {
        this.branch = branch;
        this.customer = customer;
        this.amount = amount;
        this.accountStatus = accountStatus;
    }
}
