package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value="Redis-Controller类",tags= {"测试Redis接口"})
@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	@GetMapping("/testRedis")
	public String testRedis() {
	    redisTemplate.opsForValue().set("a", "1");
	    redisTemplate.opsForValue().set("b", "2", 600);
        Object a = redisTemplate.opsForValue().get("a");
        System.out.println(a);
        return a.toString();
	}
}
