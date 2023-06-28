package com.shawn.springaopstudy01;

import com.shawn.springaopstudy01.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringAopStudy01Application {

    private static final Logger log = LoggerFactory.getLogger(SpringAopStudy01Application.class);

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(SpringAopStudy01Application.class, args);
//        MyService service = context.getBean(MyService.class);
//        log.info("service class: {}", service.getClass());
//        service.foo();
//        context.close();

        new MyService().foo();
    }

}
