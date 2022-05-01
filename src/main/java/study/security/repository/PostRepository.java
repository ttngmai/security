package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
}
