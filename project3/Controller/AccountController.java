package com.example.project3.Controller;
import com.example.project3.ApiResponse.APIResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    
    private final AccountService accountService;

//get-all-accounts-Admin
    //get-my-accounts-customer
    //add-update/{accountId}-delete-customer
    @GetMapping("/get-all-accounts")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAll());
    }
    //@AuthenticationPrincipal User user   //mean login check is login or not


    //List user's accounts
    @GetMapping("/get-my-accounts")
    public ResponseEntity getMyTodo(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyTodos(user.getId()));
    }

//----------------------------------CRUD----------------------
    //Create a new bank account
//"accountNumber":"accountNumber","balance":0
    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Account account){
        accountService.add(user.getId(),account);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Account added Successfully"));
    }
    @PutMapping("/update/{accountId}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId,@RequestBody @Valid Account account){
        accountService.update(user.getId(),accountId,account);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Account update Successfully"));
    }
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser user,@PathVariable Integer accountId){
        accountService.delete(user.getId(),accountId);

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Account deleted Successfully"));

    }

    //View account details
    @GetMapping("view-account-details/{accountId}")
public ResponseEntity ViewAccountDetails(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.viewAccountDetails(myUser.getId(),accountId));
}

//Active a bank account
    @PostMapping("active-bank-account/{accountId}")
    public ResponseEntity ActiveAbankAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountId){
        accountService.activeAbankAccount(myUser.getId(),accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Account activated successfully"));
    }


    //deposit
    @PostMapping("deposit-account/{accountId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountId,@PathVariable Double amount){
        accountService.deposit(myUser.getId(),accountId,amount);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Deposit successfully"));
    }

    //withdraw
    @PostMapping("withdraw-account/{accountId}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountId,@PathVariable Double amount){
        accountService.withdraw(myUser.getId(),accountId,amount);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Withdraw successfully"));
    }

    //
    //Transfer funds between accounts
    @PostMapping("/transfer-funds-between-accounts/{myaccountId}/{accountId}/{amount}")
    public ResponseEntity Transferfundsbetweenaccounts(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer myaccountId,@PathVariable Integer accountId,@PathVariable Double amount){
        accountService.Transferfundsbetweenaccounts(myUser.getId(),myaccountId,accountId,amount);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Amount transferred  successfully"));

    }
        //  8. Block bank account
    @PostMapping("/block-account/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Account Blocked  successfully"));

    }
}
