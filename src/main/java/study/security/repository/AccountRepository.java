package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
