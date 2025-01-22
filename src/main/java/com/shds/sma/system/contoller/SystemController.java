package com.shds.sma.system.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/system/main")
    public String systemMain() {
        return "/system/systemMain";
    }
}
