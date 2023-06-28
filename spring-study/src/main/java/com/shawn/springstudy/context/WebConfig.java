package com.shawn.springstudy.context;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author wangxiang
 * @date 2023/6/28 16:54
 * @description
 */
@Configuration
public class WebConfig {
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean registrationBean(DispatcherServlet servlet) {
        return new DispatcherServletRegistrationBean(servlet, "/");
    }

    @Bean("/hello")
    public Controller controller1() {
        return (request, response) -> {
            response.getWriter().print("hello");
            return null;
        };
    }

    @Bean("/hello2")
    public Controller controller2() {
        return (request, response) -> {
            response.getWriter().print("hello2");
            return null;
        };
    }
}
