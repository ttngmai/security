package study.security.excel;

import org.junit.jupiter.api.Test;
import study.security.dto.post.PostDetailDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelTest {
    @Test
    void excelDownload() {
        PostDetailDto post1 = new PostDetailDto(
                1L,
                "게시글1 제목",
                "게시글1 내용",
                "게시글1에 대한 댓글1",
                "게시글1에 대한 댓글2"
        );
        PostDetailDto post2 = new PostDetailDto(
                1L,
                "게시글1 제목",
                "게시글1 내용",
                "게시글1에 대한 댓글1",
                "게시글1에 대한 댓글2"
        );

        List<PostDetailDto> posts = new ArrayList<>(Arrays.asList(post1, post2));
    }
}
