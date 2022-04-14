package study.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.entity.RoleResource;
import study.security.repository.ResourcesQueryRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SecurityResourceService {
    @Autowired
    private ResourcesQueryRepository resourcesQueryRepository;

    @Transactional
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> map = new LinkedHashMap<>();
        List<RoleResource> roleResources = resourcesQueryRepository.findAllRoleResource();

        for (RoleResource roleResource : roleResources) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(roleResource.getResources().getName());

            if (map.containsKey(requestMatcher)) {
                List<ConfigAttribute> roles = map.get(requestMatcher);

                roles.add(new SecurityConfig(roleResource.getRole().getName()));
                map.replace(requestMatcher, roles);
            } else {
                List<ConfigAttribute> configAttributes = new ArrayList<>();

                configAttributes.add(new SecurityConfig(roleResource.getRole().getName()));
                map.put(requestMatcher, configAttributes);
            }
        }

        return map;
    }
}
