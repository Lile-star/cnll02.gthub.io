package com.crm;

import com.sun.media.sound.SoftTuning;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication

@MapperScan(basePackages = {"com.**.mapper"})
@EnableTransactionManagement
public class CrmApplication {


    public static void main(String[] args) {
       SpringApplication.run(CrmApplication.class, args);
    }

}
