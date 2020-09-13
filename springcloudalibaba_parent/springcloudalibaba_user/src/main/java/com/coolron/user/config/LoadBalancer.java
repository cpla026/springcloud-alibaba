package com.coolron.user.config;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡器 轮询算法
 */
public interface LoadBalancer {
    ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList);
}
