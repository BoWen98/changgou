package com.bowen.service.goods;


import com.bowen.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.service.goods
 * @ClassName: GoodsApplication
 * @Author: Bowen
 * @Description: Goods商品
 * @Date: 2019/12/1 0:44
 * @Version: 1.0.0
 */
@SpringBootApplication(scanBasePackages = "com.bowen.*")
@EnableEurekaClient
@MapperScan("com.bowen.service.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
