package study.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import study.security.dto.ResourcesDto;
import study.security.dto.resource.ResourcesDetailDto;
import study.security.entity.Resources;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourcesMapper {
    @Mapping(target = "roleName", ignore = true)
    ResourcesDto entityToDto(Resources resources);

    ResourcesDetailDto entityToDetailDto(Resources resources, List<String> roleNames);

    Resources dtoToEntity(ResourcesDto resourcesDto);
}
