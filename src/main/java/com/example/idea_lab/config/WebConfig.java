package com.example.idea_lab.config;


import com.example.idea_lab.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //登录接口和注册接口不拦截
//        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径的跨域请求
                .allowedOrigins("http://localhost:5173")  // 允许前端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                .allowCredentials(true)  // 允许发送凭证（如 Cookie 或 Authorization）
                .allowedHeaders("*");  // 允许所有请求头
    }
}
