package com.example.project3.Service;

import com.example.project3.ApiResponse.APIException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service@RequiredArgsConstructor
public class AccountService {
private final AccountRepository accountRepository;
private final CustomerRepository customerRepository;

    public List<Account> getAll() {
        if (accountRepository.findAll().isEmpty()) throw new APIException("Empty list accounts");
        else return accountRepository.findAll();
    }

    public List<Account> getMyTodos(Integer userId) {
        Customer customer = customerRepository.findCustomerById(userId);
        return accountRepository.findAllByCustomer(customer);

    }

    public void add(Integer userId, Account account) {
        Customer customer = customerRepository.findCustomerById(userId);
        account.setCustomer(customer);
        accountRepository.save(account);//don't miss is important

    }

    public void update(Integer userId, Integer aacountId, Account account) {
        Account retrive = getOneAccount(aacountId);
        if (retrive==null){throw new APIException("Account Id Not Found");}
        if (retrive.getCustomer().getId() == userId) {
            retrive.setAccountNumber(account.getAccountNumber());
            retrive.setBalance(account.getBalance());
            retrive.setActive(account.isActive());
accountRepository.save(retrive);
        }
        else {throw new APIException("Unauthorized for you");}

    }
    public void delete(Integer userId, Integer accountId){
        if (check(userId,accountId)){
            accountRepository.delete(accountRepository.findAccountById(accountId));}
        else {throw new APIException("Unauthorized for you");}
    }

    public Account viewAccountDetails(Integer userId, Integer accountId)
    {
        if (check(userId,accountId))
            return accountRepository.findAccountById(accountId);
        else throw new APIException("Unauthorized for you");

    }

    public void activeAbankAccount(Integer userId, Integer accountId){

        if (check(userId,accountId)){
            if (getOneAccount(accountId).isActive())
                throw new APIException("Account Active Already");
            else {
                Account account=accountRepository.findAccountById(accountId);
                account.setActive(true);
            accountRepository.save(account);}
        }
        else throw new APIException("Unauthorized for you");
    }



    //Deposit and withdraw money

    public void deposit(Integer userId, Integer accountId,Double amount){
        Account account = getOneAccount(accountId);
        if (check(userId,accountId)){
            if (account.isActive()){
            account.setBalance(account.getBalance()+amount);
            accountRepository.save(account);
        }}else throw new APIException("Account Not active");

    }
public void withdraw (Integer userId, Integer accountId,Double amount){
    Account account = getOneAccount(accountId);
    if (check(userId,accountId)){
        if (account.isActive()) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            } else throw new APIException("Balance Not Enough");
        }else throw new APIException("Account Not Active");
    }

}

public void Transferfundsbetweenaccounts
        (Integer userId, Integer myaccountId,Integer accountId,Double amount){
    Account account = accountRepository.findAccountById(myaccountId);
    Account accountTransfer=getOneAccount(accountId);
    if (check(userId,myaccountId)){
        if (account.isActive()&&accountTransfer.isActive()) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance()-amount);
                accountTransfer.setBalance(accountTransfer.getBalance()+amount);
                accountRepository.save(account);
                accountRepository.save(accountTransfer);

            }
        }
        else {throw new APIException("Cant transfer to account not Active");}
    }
}
public void blockAccount(Integer accountId){

        if (!getOneAccount(accountId).isActive())
            throw new APIException("Account Block Already");
        else {
            Account account=accountRepository.findAccountById(accountId);
            account.setActive(false);
            accountRepository.save(account);}
}

    public boolean check(Integer userId,Integer accountId ){
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){throw new APIException("account Id Not Found");}
        if (account.getCustomer().getId()==userId)
        { return true;}
            else
        {throw new APIException("Unauthorized for you");}
    }

    public Account getOneAccount(Integer aacountId){
        if (accountRepository.findAccountById(aacountId)==null)
            throw new APIException("account Id Not Found");
        else return accountRepository.findAccountById(aacountId);
    }
}