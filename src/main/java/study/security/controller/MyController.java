package study.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
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
}
