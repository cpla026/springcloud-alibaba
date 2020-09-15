package com.coolron.article.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Value("${server.port}")
    private String port;

    @GetMapping("/dto")
    public List list() {

        System.out.println("+++article++serverport:" + port);

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
}
