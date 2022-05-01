package study.security.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "POST")
@SequenceGenerator(name = "POST_SEQ_GEN", sequenceName = "POST_SEQ", initialValue = 1, allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GEN")
    private Long id;

    private String title;
    private String content;

    @BatchSize(size = 100)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;
}
