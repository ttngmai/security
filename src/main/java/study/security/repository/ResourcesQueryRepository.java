package study.security.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.security.dto.resource.ResourcesDetailDto;
import study.security.entity.QResources;
import study.security.entity.Resources;
import study.security.entity.RoleResource;
import study.security.mapper.ResourcesMapper;

import java.util.List;
import java.util.stream.Collectors;

import static study.security.entity.QResources.resources;
import static study.security.entity.QRole.role;
import static study.security.entity.QRoleResource.roleResource;

@RequiredArgsConstructor
@Repository
public class ResourcesQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final ResourcesMapper resourcesMapper;

    public List<RoleResource> findAllRoleResource() {

        return queryFactory
                .selectFrom(roleResource)
                .join(roleResource.role, role).fetchJoin()
                .join(roleResource.resources, resources).fetchJoin()
                .orderBy(resources.orderNum.asc())
                .fetch();
    }

    public ResourcesDetailDto findResourceWithRoles(long id) {
        Resources resource = queryFactory
                .selectFrom(QResources.resources)
                .where(QResources.resources.id.eq(id))
                .fetchOne();

        if (resource == null) {
            return null;
        }

        List<RoleResource> roleResources = queryFactory
                .selectFrom(roleResource)
                .join(roleResource.resources, resources).fetchJoin()
                .join(roleResource.role, role).fetchJoin()
                .where(roleResource.resources.id.in(resource.getId()))
                .fetch();

        List<String> roleNames = roleResources
                .stream()
                .map(r -> r.getRole().getName())
                .collect(Collectors.toList());

        return resourcesMapper.entityToDetailDto(resource, roleNames);
    }
}
