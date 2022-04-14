package study.security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.security.entity.*;
import study.security.repository.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class SampleDataLoad {
    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitDataService {
        @PersistenceContext
        private EntityManager em;

        private final RoleRepository roleRepository;
        private final AccountRepository accountRepository;
        private final AccountRoleRepository accountRoleRepository;
        private final ResourcesRepository resourcesRepository;
        private final RoleResourceRepository roleResourceRepository;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void init() {
            Role roleUser = createRoleIfNotFound("ROLE_USER");
            Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");

            Account account1 = createAccountIfNotFound("user", "1234");
            Account account2 = createAccountIfNotFound("admin", "1234");

            if (accountRoleRepository.findAll().size() == 0) {
                createAccountRole(account1, roleUser);
                createAccountRole(account2, roleUser);
                createAccountRole(account2, roleAdmin);
            }

            Resources resource1 = createResourceIfNotFound("/user", "GET", "url", 1);
            Resources resource2 = createResourceIfNotFound("/admin", "GET", "url", 1);

            if (roleResourceRepository.findAll().size() == 0) {
                createRoleResource(roleUser, resource1);
                createRoleResource(roleAdmin, resource2);
            }
        }

        @Transactional
        public Role createRoleIfNotFound(String name) {
            Role role = roleRepository.findByName(name);

            if (role == null) {
                role = Role.builder()
                        .name(name)
                        .build();
            }

            return roleRepository.save(role);
        }

        @Transactional
        private Account createAccountIfNotFound(String username, String password) {
            Account account = accountRepository.findByUsername(username);

            if (account == null) {
                account = Account.builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .build();
            }

            return accountRepository.save(account);
        }

        @Transactional
        private void createAccountRole(Account account, Role role) {
            AccountRole accountRole = new AccountRole();

            accountRole.setAccount(account);
            accountRole.setRole(role);

            em.persist(accountRole);
        }

        @Transactional
        public Resources createResourceIfNotFound(String name, String httpMethod, String type, int orderNum) {
            Resources resources = resourcesRepository.findByNameAndHttpMethod(name, httpMethod);

            if (resources == null) {
                resources = Resources.builder()
                        .name(name)
                        .httpMethod(httpMethod)
                        .type(type)
                        .orderNum(orderNum)
                        .build();
            }

            return resourcesRepository.save(resources);
        }

        @Transactional
        public void createRoleResource(Role role, Resources resources) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRole(role);
            roleResource.setResources(resources);

            em.persist(roleResource);
        }
    }
}
