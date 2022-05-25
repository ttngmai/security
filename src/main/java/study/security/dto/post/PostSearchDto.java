package study.security.dto.post;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostSearchDto {
    private String title;
    private String content;
}
