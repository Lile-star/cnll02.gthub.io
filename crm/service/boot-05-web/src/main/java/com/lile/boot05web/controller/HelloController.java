package com.lile.boot05web.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class HelloController {

    @RequestMapping("/001.jpg")
    public Object hello(){
        return "aaa";
    }
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }

    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }


    @PutMapping("/user")
    public String putUser(){
        return "PUT-张三";
    }

    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-张三";
    }
}
