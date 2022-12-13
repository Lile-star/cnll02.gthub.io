package com.lile.admin.controller;

import com.lile.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {


    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        System.out.println(user);
        if (StringUtils.hasLength(user.getUserName())&&"123456".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }
    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            return "main";
        }else{
            model.addAttribute("msg", "请重新登录");
            return "login";
        }

    }
}
