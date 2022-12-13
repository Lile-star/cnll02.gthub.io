package com.lile.boot05web.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @RequestMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg", "成功");
        request.setAttribute("code", 20);
        return "forward:/success";
    }
    @ResponseBody
    @RequestMapping("/success")
    public Map success(@RequestAttribute("code") String code,
                       HttpServletRequest request){
        Object msg1 = request.getAttribute("msg");
        Map<String,Object> map=new HashMap<>();
        map.put("msg", msg1);
        map.put("code", code);
        return map;
    }
}
