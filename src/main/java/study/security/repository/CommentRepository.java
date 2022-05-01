package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByContent(String content);
}
