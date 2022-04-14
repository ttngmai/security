package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.AccountRole;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
}
