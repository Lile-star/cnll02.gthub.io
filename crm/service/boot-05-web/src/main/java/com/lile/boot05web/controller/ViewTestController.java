package com.lile.boot05web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewTestController {

    @RequestMapping("/x1")
    public String x1(Model model){
        model.addAttribute("msg", "你好,世界");
        model.addAttribute("link", "www.baidu.com");
        return "success";
    }

}
