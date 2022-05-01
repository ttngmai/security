package study.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.dto.AccountDto;
import study.security.entity.Account;
import study.security.mapper.AccountMapper;
import study.security.repository.AccountRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> list() {
        List<Account> accounts = accountRepository.findAll();

        return accountMapper.entitiesToDtos(accounts);
    }
}
