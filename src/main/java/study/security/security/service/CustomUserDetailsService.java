package study.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.entity.Account;
import study.security.repository.AccountRepository;
import study.security.security.user.AccountContext;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }

        List<String> accountRoles = account.getAccountRoles()
                        .stream()
                        .map(accountRole -> accountRole.getRole().getName())
                        .collect(Collectors.toList());

        List<SimpleGrantedAuthority> roles = accountRoles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}
