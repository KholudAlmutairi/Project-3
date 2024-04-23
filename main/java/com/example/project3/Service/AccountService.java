package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
  //CRUD
    public List<Account> getAllAccounts() {//admin
        return accountRepository.findAll();
    }

//    public Account createAccount(Account account) {
//        return accountRepository.save(account);
//    }


//    public Account getMyAccount(Integer customerId){
//        Account account=authRepository.findUserById(customerId);
//        Customer customer=authRepository.findUserById(customerId);
//        return accountRepository.findAllByCustomer(customer);
//
//    }
//

    public void addAccount(Integer userId,Account account){
        User user=authRepository.findUserById(userId);
       // account.setUser(user);
       // account.setCustomer(customer());
        accountRepository.save(account);
    }

    public void updateAccount(Integer userId, Account account,Integer accountId) {
        Account a = accountRepository.findAccountById(accountId);
            if(a==null){
               throw new ApiException("Account not found" );
            }

            if(a.getCustomer().getId()!=userId){
                throw new ApiException("User id not found" );
            }

            a.setAccountNumber(account.getAccountNumber());
            a.setBalance(account.getBalance());
            a.setActive(account.isActive());

           accountRepository.save(a);
    }

    public void deleteAccount(Integer userId,Integer acountId) {
        Account a=accountRepository.findAccountById(acountId);
        if (a==null) {
            throw new  ApiException("Account not found with id ");
        }
        if(!a.getCustomer().getId().equals(userId)){
            throw new ApiException("Not found user id");
        }
        accountRepository.delete(a);
    }




    public Account accountDetail(Integer userId , Integer accountId) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);


        if (account.getCustomer().getId() == user.getId()) {
            return account;
        }

        return null;
    }



    public void deposit(Integer userId , Integer accountId, Integer amount) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);


        if (account.getCustomer().getId() == user.getId()) {
            account.setBalance(account.getBalance() + amount);

            accountRepository.save(account);
        }

    }


    public void withdraw(Integer userId , Integer accountId, Integer amount) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);

        if (account.getCustomer().getId() == user.getId()) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }
        }
    }



    //Transfer funds between accounts
    public void transferFunds(Integer userAccountId ,  Integer anotherUserId ,  Integer amount) {

        Account account = accountRepository.findAccountById(userAccountId);
        Account anotherAccount = accountRepository.findAccountById(anotherUserId);

        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            anotherAccount.setBalance(anotherAccount.getBalance() + amount);
            accountRepository.save(account);
            accountRepository.save(anotherAccount);
        }

    }

        public Account deposit(Integer id, double amount) {
        Account account = accountRepository.findAccountById(id);
        if(account==null)
            throw new ApiException("Account not found");
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Integer id, double amount) {
        Account account = accountRepository.findAccountById(id);
         if(account==null)
             throw new ApiException("Account not found");
        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

}
