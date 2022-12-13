package com.lile.config;

import com.lile.bean.Car;
import com.lile.bean.Pet;
import com.lile.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties(Car.class)
public class Myconfig {


    @Bean
    @Primary
    public Car car1(){
        return new Car("泵吃", 1000000);
    }

    @Bean//给容器中添加组件,以方法名作为组件的ID,返回值就是组件的类型
    public User user01(){
        return new User("张三", 18);
    }
    @Bean
    public Pet tomcat(){
        return new Pet("tomcat");
    }

}
