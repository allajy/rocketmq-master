package com.bxc.payb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.bxc.payb.mapper")
@ComponentScan(basePackages = {"com.bxc.payb.*", "com.bxc.payb.config.*"})
public class MainConfig {

}
