package com.shawn.springstudy.ref;

import com.shawn.springstudy.model.Student;
import com.shawn.springstudy.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxiang
 * @date 2023/6/28 10:29
 * @description
 */
@Configuration
public class BeansConfig {

    @Bean(name = "user", initMethod = "doInit", destroyMethod = "doDestroy")
    public User create() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(18);
        return user;
    }

    @Bean(name = "student", initMethod = "doInit", destroyMethod = "doDestroy")
    public Student createStudent() {
        Student student = new Student();
        student.setName("lisiiiiiii");
        student.setAge(11118);
        return student;
    }
}