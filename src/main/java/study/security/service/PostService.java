package study.security.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import study.security.dto.post.PostDetailDto;
import study.security.dto.post.PostSearchDto;
import study.security.entity.Post;
import study.security.entity.QPost;
import study.security.mapper.PostMapper;

import java.util.List;

import static study.security.entity.QPost.post;

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
                .selectFrom(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        List<PostDetailDto> posts = postMapper.entitiesToDetailDtos(entities);

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    @Transactional(readOnly = true)
    public Page<PostDetailDto> list(PostSearchDto condition, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize());

        List<Post> entities = queryFactory
                .selectFrom(post)
                .where(
                        titleContains(condition.getTitle()),
                        contentContains(condition.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(
                    titleContains(condition.getTitle()),
                    contentContains(condition.getContent())
                );

        List<PostDetailDto> posts = postMapper.entitiesToDetailDtos(entities);

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? post.title.contains(title) : null;
    }

    private BooleanExpression contentContains(String content) {
        return StringUtils.hasText(content) ? post.content.contains(content) : null;
    }
}
