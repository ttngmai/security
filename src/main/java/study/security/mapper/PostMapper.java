package study.security.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import study.security.dto.post.PostDetailDto;
import study.security.entity.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDetailDto entityToDetailDto(Post post);

    List<PostDetailDto> entitiesToDetailDtos(List<Post> posts);

    @AfterMapping
    default void setAfter(@MappingTarget PostDetailDto dto, Post post) {
        if (post.getComments() != null) {
            if (post.getComments().size() >= 1) {
                dto.setComment1Content(post.getComments().get(0).getContent());
            }

            if (post.getComments().size() >= 2) {
                dto.setComment2Content(post.getComments().get(1).getContent());
            }
        }
    }
}
