package study.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import study.security.dto.post.PostDetailDto;
import study.security.excel.OneSheetExcelFile;
import study.security.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Controller
public class MyController {
    private final PostService postService;

    @GetMapping("/")
    public String showMainPage() {
        return "index";
    }

    @GetMapping("/user")
    public String showUserpage() {
        return "user";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/excel")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        OneSheetExcelFile<PostDetailDto> excelFile = new OneSheetExcelFile<>(PostDetailDto.class);
        Pageable pageable = PageRequest.of(1, 100);

        Page<PostDetailDto> postPageObj;
        do {
            // 엑셀 렌더링에 필요한 데이터가 담겨있는 Page<PostDetailDto>를 받아옵니다.
            postPageObj = postService.findAll(pageable);

            // 받아온 데이터를 기반으로 엑셀에 새로운 row를 추가 합니다.
            excelFile.addRows(postPageObj.getContent());

            // 다음 페이지를 요청하는 pageable 객체를 세팅합니다.
            pageable = pageable.next();
        } while ((postPageObj.hasNext()));

        String filename = URLEncoder.encode("게시글 정보", StandardCharsets.UTF_8);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));

        excelFile.write(response.getOutputStream());
    }
}
