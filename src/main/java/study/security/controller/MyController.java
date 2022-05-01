package study.security.controller;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import study.security.dto.AccountDto;
import study.security.dto.post.PostDetailDto;
import study.security.excel.OneSheetExcelFile;
import study.security.service.AccountService;
import study.security.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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
        // 엑셀 렌더링에 필요한 DTO를 가져옵니다.
        List<PostDetailDto> posts = postService.findAll();

        OneSheetExcelFile<PostDetailDto> excelFile = new OneSheetExcelFile<>(posts, PostDetailDto.class);

        String filename = URLEncoder.encode("게시글 정보", "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));

        excelFile.write(response.getOutputStream());
    }

    private void applyCellStyle(CellStyle cellStyle, Color color) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
        xssfCellStyle.setFillForegroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
    }
}
