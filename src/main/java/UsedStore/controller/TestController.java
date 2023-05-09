package UsedStore.controller;

import UsedStore.model.Test;
import UsedStore.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public String findAll(Model model) {
        List<Test> testList = testService.findAll();
        model.addAttribute("testList", testList);
        return "test.html";
    }
    @GetMapping("/")
    public String find() {
        return "test.html";
    }
}
