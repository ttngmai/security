package study.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.security.entity.RoleResource;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourcesDto {
    private Long id;
    private String name;
    private String httpMethod;
    private String type;
    private int orderNum;
    private String roleName;
    private Set<RoleResource> roleResources = new HashSet<>();
}
