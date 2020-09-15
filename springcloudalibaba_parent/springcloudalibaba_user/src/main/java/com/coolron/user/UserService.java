package com.coolron.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserService {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserService.class, args);
//        while(true) {
            //When configurations are refreshed dynamically, they will be updated in the Enviroment, therefore here we retrieve configurations from Environment every other second.
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.err.println("user name :" + userName + "; age: " + userAge);
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

 /*   @RestController
    public class TestController {

//        private final RestTemplate restTemplate;

        @Autowired
        private  RestTemplate restTemplate;
//        public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

        @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://article-service/article/echo/" + str, String.class);
        }
    }*/

}
