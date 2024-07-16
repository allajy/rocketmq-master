package com.bxc.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Bixc
 * @date: 2024/07/13 14:57
 **/
@Configuration
@MapperScan(basePackages = "com.bxc.store.mapper")
@ComponentScan(basePackages = {"com.bxc.store.*", "com.bxc.store.config.*"})
public class MainConfig {

}