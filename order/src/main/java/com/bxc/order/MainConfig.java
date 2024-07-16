package com.bxc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Bixc
 * @date: 2024/07/13 14:25
 **/
@Configuration
@MapperScan(basePackages = "com.bxc.order.mapper")
@ComponentScan(basePackages = {"com.bxc.order","com.bxc.order.config.*"})
public class MainConfig {
}
