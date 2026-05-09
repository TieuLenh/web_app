package backend.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class pageController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageFile", "pages/home");
        model.addAttribute("pageName", "home");
        model.addAttribute("contentFile", "fragments/content");
        model.addAttribute("content", "content");
        return "layout";
    }
}
