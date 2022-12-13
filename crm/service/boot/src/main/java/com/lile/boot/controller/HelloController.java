package com.lile.boot.controller;

import com.lile.boot.bean.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {
    @Resource
    Person person;

    @RequestMapping("/person")
    public Person person(){

        return person;
    }
}
