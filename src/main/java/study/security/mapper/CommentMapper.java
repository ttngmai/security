package study.security.mapper;

import org.mapstruct.Mapper;
import study.security.dto.CommentDto;
import study.security.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto entityToDto(Comment comment);
}
