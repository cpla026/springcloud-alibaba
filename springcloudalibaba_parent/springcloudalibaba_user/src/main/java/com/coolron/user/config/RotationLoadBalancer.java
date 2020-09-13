package com.coolron.user.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡器 轮询算法
 */
@Component
public class RotationLoadBalancer implements LoadBalancer {

    /**
     * 原子类，保证线程安全
     * 从 0 开始计数
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 轮询算法  取模
     * 访问次数 % IP 列表的 size
     * @param serviceInstanceList
     * @return
     */
    @Override
    public ServiceInstance getSingleAddress(List<ServiceInstance> serviceInstanceList) {

        // incrementAndGet 每访问一次 ++
        int index = atomicInteger.incrementAndGet() % serviceInstanceList.size();
        ServiceInstance serviceInstance = serviceInstanceList.get(index);

        return serviceInstance;

    }

}
