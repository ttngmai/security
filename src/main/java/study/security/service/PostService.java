package study.security.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.dto.post.PostDetailDto;
import study.security.entity.Post;
import study.security.entity.QPost;
import study.security.mapper.PostMapper;
import study.security.repository.PostRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final JPAQueryFactory queryFactory;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostDetailDto> findAll() {
        List<Post> posts = queryFactory.selectFrom(QPost.post).fetch();

        return postMapper.entitiesToDetailDtos(posts);
    }
}
