package study.security.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import study.security.dto.post.PostDetailDto;
import study.security.dto.post.PostSearchDto;
import study.security.excel.OneSheetExcelFile;
import study.security.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MyController {
    private final PostService postService;

    @GetMapping("/")
    public String showMainPage() {
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @ResponseBody
    @GetMapping(value = "/posts", produces = "application/json; charset=UTF-8")
    public Page<PostDetailDto> list(PostSearchDto condition, @PageableDefault Pageable pageable) {
        Page<PostDetailDto> list = postService.list(condition, pageable);

        return list;
    }

    @ResponseBody
    @PostMapping(value = "/posts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> test(@RequestBody Map<String, Object> params) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<PostSearchDto> postSearchDtoList
                = objectMapper.convertValue(
                        params.get("formData"),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, PostSearchDto.class));

        postSearchDtoList.stream().forEach(System.out::println);


        return new ResponseEntity<>(postSearchDtoList, HttpStatus.OK);
    }

    @GetMapping("/excel")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        OneSheetExcelFile<PostDetailDto> excelFile = new OneSheetExcelFile<>(PostDetailDto.class);
        Pageable pageable = PageRequest.of(1, 100);

        Page<PostDetailDto> postPageObj;
        do {
            // ?????? ???????????? ????????? ???????????? ???????????? Page<PostDetailDto>??? ???????????????.
            postPageObj = postService.findAll(pageable);

            // ????????? ???????????? ???????????? ????????? ????????? row??? ?????? ?????????.
            excelFile.addRows(postPageObj.getContent());

            // ?????? ???????????? ???????????? pageable ????????? ???????????????.
            pageable = pageable.next();
        } while ((postPageObj.hasNext()));

        String filename = URLEncoder.encode("????????? ??????", StandardCharsets.UTF_8);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));

        excelFile.write(response.getOutputStream());
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public String apiMessage() {
        return "messages ok";
    }
}
