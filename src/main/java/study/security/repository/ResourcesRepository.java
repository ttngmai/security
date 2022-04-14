package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.Resources;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    Resources findByNameAndHttpMethod(String name, String httpMethod);
}
