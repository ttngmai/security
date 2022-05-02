package study.security.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.dto.post.PostDetailDto;
import study.security.entity.Post;
import study.security.entity.QPost;
import study.security.mapper.PostMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final JPAQueryFactory queryFactory;
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public Page<PostDetailDto> findAll(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 100);

        List<Post> entities = queryFactory
                .selectFrom(QPost.post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(QPost.post.count())
                .from(QPost.post);

        List<PostDetailDto> posts = postMapper.entitiesToDetailDtos(entities);

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }
}
