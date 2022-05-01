package study.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import study.security.dto.post.PostDetailDto;
import study.security.entity.Post;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CommentMapper.class })
public interface PostMapper {
    @Mapping(target="comment1Content", expression="java(post.getComments() != null && post.getComments().size() >= 1 ? post.getComments().get(0).getContent() : null)")
    @Mapping(target="comment2Content", expression="java(post.getComments() != null && post.getComments().size() >= 2 ? post.getComments().get(1).getContent() : null)")
    PostDetailDto entityToDetailDto(Post post);

    List<PostDetailDto> entitiesToDetailDtos(List<Post> posts);
}
