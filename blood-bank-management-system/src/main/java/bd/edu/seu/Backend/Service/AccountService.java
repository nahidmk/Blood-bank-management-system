package bd.edu.seu.Backend.Service;

import bd.edu.seu.Backend.Model.Account;
import bd.edu.seu.Backend.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void SaveAccount(Account account)
    {
        accountRepository.save(account);
    }

    public void AccountDelete(Account account)
    {
        accountRepository.delete(account);
    }
    public List<Account> findAll()
    {
        return accountRepository.findAll();
    }
    public List<Account> findByTransactionDate(LocalDate date)
    {
        return accountRepository.findByTransactionDate(date);
    }
}
