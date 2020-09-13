package com.coolron.user.controller;

import com.coolron.user.config.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 用户服务测试类
 */
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

    /**
     * 作者对应的文章
     *
     */
    @GetMapping("getUserArticles")
    public Object getUserArticles(){
        List<ServiceInstance> instances = discoveryClient.getInstances("article-service");
//        ServiceInstance serviceInstance = instances.get(0);

        // 负载均衡器
        ServiceInstance serviceInstance = loadBalancer.getSingleAddress(instances);

        URI uri = serviceInstance.getUri();

        List list = restTemplate.getForObject(uri + "/article/test/dto", List.class);
        return list;
    }

}
