package com.eteration.simplebanking.controller;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import com.eteration.simplebanking.model.dto.SuccessfulTransactionStatus;
import com.eteration.simplebanking.model.dto.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account/{rest.version}/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
            return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) throws InsufficientBalanceException {
            Account account = accountService.findAccount(accountNumber);
            account.post(transaction);
            accountService.save(account);
            return ResponseEntity.ok(new SuccessfulTransactionStatus( transaction.getApprovalCode().toString()));


    }

    @PostMapping("debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException {
        var account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.save(account);
        return ResponseEntity.ok(new SuccessfulTransactionStatus(transaction.getApprovalCode().toString()));


    }

    @PostMapping("phoneBillPayment/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBillPayment(@PathVariable String accountNumber, @RequestBody PhoneBillPaymentTransaction transaction) throws InsufficientBalanceException {
        var account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.save(account);
        return ResponseEntity.ok(new SuccessfulTransactionStatus( transaction.getApprovalCode().toString()));

    }
}