package com.coolron.user.controller;

import com.coolron.user.config.LoadBalancer;
import com.coolron.user.provider.ArticleTestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 用户服务测试类
 */
// dynamic configuration update.
@RefreshScope
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    // 自定义 负载均衡器
    @Autowired
    private LoadBalancer loadBalancer;

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ArticleTestFeign articleTestFeign;


    /***************************** restTemplate start ********************************/

    /**
     * 作者对应的文章
     * <p>
     * 自定义 负载均衡器
     */
    @GetMapping("getUserArticles")
    public Object getUserArticles() {
        List<ServiceInstance> instances = discoveryClient.getInstances("article-service");
//        ServiceInstance serviceInstance = instances.get(0);

        // 自定义负载均衡器
        ServiceInstance serviceInstance = loadBalancer.getSingleAddress(instances);

        URI uri = serviceInstance.getUri();

        List list = restTemplate.getForObject(uri + "/article/test/dto", List.class);
        return list;
    }

    /**
     * 作者对应的文章
     * <p>
     * ribbon 负载均衡器
     *
     * @return
     */
    @GetMapping("getUserArticlesRibbon")
    public Object getUserArticlesRibbon() {
        // restTemplate 负载均衡器已经实现
        List list = restTemplate.getForObject("http://article-service/article/test/dto", List.class);
        return list;
    }

    /***************************** restTemplate end ********************************/

    /*****************************  openFeign start ********************************/

    /**
     * 作者对应的文章
     * <p>
     * openfeign
     *
     * @return
     */
    @GetMapping("getUserArticlesFeign")
    public Object getUserArticlesFeign() {
        List list = articleTestFeign.list();
        return list;
    }

    /*****************************  openFeign end   ********************************/

    /*****************************  config start ********************************/

    @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private String age;

    @GetMapping("/config/{param}")
    public String config(@PathVariable String param) {

        String result = "";

        switch (param) {
            case "name":
                result = name;
                break;
            case "age":
                result = age;
                break;
            default:
                result = name + age;
                break;
        }

        return result;
    }

    /*****************************  config end  **********************************/

}
