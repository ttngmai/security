package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.RoleResource;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {
}
