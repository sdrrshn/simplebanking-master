package com.eteration.simplebanking.model;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", updatable = false)
    private OffsetDateTime date;

    @Column(name = "amount")
    private double amount;
    @Column(name = "type", updatable = false)
    private String type;
    @Column(name = "approvalCode", updatable = false, unique = true)
    private UUID approvalCode;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;




    public Transaction(String type) {
        this.date = OffsetDateTime.now();
        this.type = type;
        this.approvalCode = UUID.randomUUID();
    }

    public Transaction(double amount, String type) {
        this.date = OffsetDateTime.now();
        this.amount = amount;
        this.type = type;
        this.approvalCode = UUID.randomUUID();
    }

    public abstract void post(Account account) throws InsufficientBalanceException;
    public OffsetDateTime getDate() {
        return date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return type;
    }
    public UUID getApprovalCode() {
        return approvalCode;
    }

}
