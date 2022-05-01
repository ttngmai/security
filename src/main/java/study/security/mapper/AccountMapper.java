package study.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import study.security.dto.AccountDto;
import study.security.dto.account.AccountDetailDto;
import study.security.entity.Account;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account dtoToEntity(AccountDto accountDto);

    Account detailDtoToEntity(AccountDetailDto accountDetailDto);

    List<AccountDto> entitiesToDtos(List<Account> accounts);

    AccountDto entityToDto(Account account);

    AccountDetailDto entityToDetailDto(Account account, List<String> roleNames);
}
