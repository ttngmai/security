package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
