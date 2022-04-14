package study.security.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.security.entity.QRole;
import study.security.entity.Role;

@RequiredArgsConstructor
@Repository
public class RoleQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Role findByRoleName(String name) {
        Role role = queryFactory
                .selectFrom(QRole.role)
                .where(QRole.role.name.eq(name))
                .fetchOne();

        return role;
    }
}
