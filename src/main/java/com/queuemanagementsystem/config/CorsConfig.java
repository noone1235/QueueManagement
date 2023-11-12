//package com.queuemanagementsystem.config;
//
//import org.springframework.beans.factory.ListableBeanFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.autoconfigure.web.WebProperties;
//import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.web.servlet.config.annotation.CorsRegistration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//@EnableWebSecurity
//public class CorsConfig{
//
//    protected void configure(AuthenticationManagerBuilder auth){
//
//    }
//    @Bean
//    public WebMvcConfigurer corsConfiguration(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                CorsRegistration corsRegistration =registry.addMapping("/organization/getActiveQueues")
//                        .allowedOrigins("http://127.0.0.1:3000")
//                        .allowedMethods("*") // Add other methods if needed
//                        .allowedHeaders("*") // Add other headers if needed
//                        .allowCredentials(true);
//                //corsRegistration.allowedOrigins().forEach(origin ->
//                        System.out.println("Allowed origin: " + corsRegistration.allowedOrigins());
//            }
//        };
//    }
//}
