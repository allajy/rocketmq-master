package com.bxc.order.config.hystrix;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Bixc
 * @date: 2024/07/15 09:57
 **/
@Configuration
public class HystrixConfig {

    // 用来拦截处理HystrixCommand注解
    @Bean
    public HystrixCommandAspect hystrixAspect(){
        return new HystrixCommandAspect();
    }

    // 用来像监控中心Dashboard发送stream消息
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        bean.addUrlMappings("/hystrix.stream");
        return bean;
    }

}
