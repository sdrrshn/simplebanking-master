package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exceptions.AmountException;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "account")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "owner", nullable = false)
    private String owner;
    @Column(name = "accountNumber", nullable = false,unique = true)
    private String accountNumber;
    @Column(name = "balance", nullable = false)
    private double balance;
    @Column(name = "createDate", nullable = false)
    private OffsetDateTime createDate=OffsetDateTime.now();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;




    public Account(Long id, String owner, String accountNumber, double balance, List<Transaction> transactions) {
        this.id = id;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        transactions = new ArrayList<>();
    }

    public Account() {
        this.balance = 0;
        transactions = new ArrayList<>();
    }

    public String post(Transaction transaction) throws InsufficientBalanceException {
       transaction.post(this);
        transaction.setAccount(this);
        transactions.add(transaction);
        return transaction.getApprovalCode().toString();
    }


    public void credit(double amount)  {
        if (amount < 0) {
            throw new AmountException("amount must be greater than 0");
        }
        balance += amount;
    }

    public void debit(double amount) throws  InsufficientBalanceException {
        if (amount < 0) {
            throw new AmountException("amount must be greater than 0");
        }
        if (balance - amount < 0) {
            throw new InsufficientBalanceException();
        }
        balance -= amount;
    }

    public String getOwner() {
        return owner;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public double getBalance() {
        return balance;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }


    public void deposit(double amount) throws AmountException {
        credit(amount);
    }

    public void withdraw(double amount) throws AmountException, InsufficientBalanceException {
        debit(amount);
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }



    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
