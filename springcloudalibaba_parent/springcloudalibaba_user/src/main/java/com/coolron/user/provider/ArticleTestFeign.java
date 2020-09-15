package com.coolron.user.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * article-service 服务接口
 *
 */
@FeignClient(name = "article-service")
public interface ArticleTestFeign {

    @GetMapping("/article/test/dto")
    public List list();

}
