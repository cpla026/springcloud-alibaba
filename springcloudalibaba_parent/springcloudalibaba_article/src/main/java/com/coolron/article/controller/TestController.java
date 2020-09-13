package com.coolron.article.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 文章服务测试类
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/dto")
    public List list() {

        List<Map<Object, Object>> list = Lists.newArrayList();

        Map<Object, Object> articleMap1 = Maps.newHashMap();
        Map<Object, Object> articleMap2 = Maps.newHashMap();

        articleMap1.put("id", 1);
        articleMap1.put("name", "团长");

        articleMap2.put("id", 2);
        articleMap2.put("name", "偷影子的人");

        list.add(articleMap1);
        list.add(articleMap2);

        return list;
    }
}
