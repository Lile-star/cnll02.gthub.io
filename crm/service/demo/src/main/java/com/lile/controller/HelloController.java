package com.lile.controller;

import com.lile.bean.Car;
import com.lile.config.Myconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Qualifier("car1")
    @Autowired
    Car car;
    @Autowired
    Myconfig myconfig;
    @RequestMapping("/car1")
    public Object car(){

        return car;
    }

}
