package com.lile;

import com.lile.bean.Car;
import com.lile.bean.Pet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sound.midi.Soundbank;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        //返回到IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        //查看容器里面的组件
//        String[] names = run.getBeanDefinitionNames();
//        for (String name:names){
//            System.out.println(name);
//        }
        //从容器中获取组件
        Pet tom = run.getBean("tomcat", Pet.class);
        Pet tom1 = run.getBean("tomcat", Pet.class);
        System.out.println(tom==tom1);
        Car bean = run.getBean(Car.class);
        Object car1 = run.getBean("car1");
        System.out.println(car1);
        System.out.println(bean);

    }

}
