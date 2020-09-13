package com.coolron.article;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章服务(服务提供者)
 */
@SpringBootApplication
// 通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能
@EnableDiscoveryClient
public class ArticleService {

    public static void main(String[] args) {
        SpringApplication.run(ArticleService.class, args);
    }

    @RestController
    class EchoController {
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }

}
