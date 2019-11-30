package com.bowen.changgou.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.changgou.eureka
 * @ClassName: EurekaApplication
 * @Author: Bowen
 * @Description: 注册中心
 * @Date: 2019/11/30 23:22
 * @Version: 1.0.0
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
