package com.bowen.service.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.service.goods
 * @ClassName: GoodsApplication
 * @Author: Bowen
 * @Description: Goods商品
 * @Date: 2019/12/1 0:44
 * @Version: 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.bowen.service.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
