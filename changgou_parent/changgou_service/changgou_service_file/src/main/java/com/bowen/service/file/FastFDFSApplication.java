package com.bowen.service.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.service.file
 * @ClassName: FastFDFSApplication
 * @Author: Bowen
 * @Description: 分布式上传文件
 * @Date: 2019/12/1 21:45
 * @Version: 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class FastFDFSApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastFDFSApplication.class, args);
    }
}
