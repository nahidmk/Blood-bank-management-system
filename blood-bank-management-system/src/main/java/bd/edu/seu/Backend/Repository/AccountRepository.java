package bd.edu.seu.Backend.Repository;

import bd.edu.seu.Backend.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    public List<Account> findByTransactionDate(LocalDate date);
}
