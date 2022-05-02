package study.security.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.security.excel.ExcelColumn;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDetailDto {
    @ExcelColumn(headerName = "아이디")
    private Long postId;

    @ExcelColumn(headerName = "게시글 제목")
    private String title;

    @ExcelColumn(headerName = "게시글 내용")
    private String content;

    @ExcelColumn(headerName = "댓글 1 내용")
    private String comment1Content;

    @ExcelColumn(headerName = "댓글 2 내용")
    private String comment2Content;

//    private List<CommentDto> comments;
}
