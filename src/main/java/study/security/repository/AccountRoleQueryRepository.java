package study.security.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static study.security.entity.QAccountRole.accountRole;
import static study.security.entity.QRole.role;

@RequiredArgsConstructor
@Repository
public class AccountRoleQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<String> findRoleNameByUserId(Long id) {
        return queryFactory
                .select(role.name)
                .from(accountRole)
                .join(accountRole.role, role)
                .where(accountRole.account.id.eq(id))
                .fetch();
    }

    public void deleteByAccountIdAndRoleId(Long accountId, Long roleId) {
        queryFactory
                .delete(accountRole)
                .where(
                        accountRole.account.id.eq(accountId),
                        accountRole.role.id.eq(roleId)
                )
                .execute();
    }
}
