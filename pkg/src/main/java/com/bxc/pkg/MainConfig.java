package com.bxc.pkg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.bxc.pkg.mapper")
@ComponentScan(basePackages = {"com.bxc.pkg.*", "com.bxc.pkg.config.*"})
public class MainConfig {

}
