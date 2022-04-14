package study.security.mapper;

import org.mapstruct.Mapper;
import study.security.dto.RoleDto;
import study.security.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role dtoToEntity(RoleDto roleDto);

    RoleDto entityToDto(Role role);
}
