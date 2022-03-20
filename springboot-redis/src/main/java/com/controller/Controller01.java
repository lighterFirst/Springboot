package com.controller;

import com.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Random;

@RestController
public class Controller01 {

    @Autowired
    private  RedisTemplate redisTemplate;
    @Autowired
    private  RedisUtil redisUtil;
@RequestMapping("/redis/{id}")
    public Object test01(@PathVariable String id){
    Jedis jedis = new Jedis("192.168.26.140",6379);
    jedis.auth("redis123456");
    redisTemplate.opsForValue().set(id,500);
    Object s1 =  redisTemplate.opsForValue().get(id);
    return s1;

}

    @RequestMapping("/secondkill/{id}")
    @ResponseBody
    public Object test02(@PathVariable String id) throws IOException {
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder();
    for(int i = 0;i<5;i++){
        int k =random.nextInt(9);
        stringBuilder.append(k);
    }
    //秒杀成功用户名单
    String userid = String.valueOf(stringBuilder);
    //商品id
    Object ok = redisUtil.deSecKill(userid,id);
    return ok;
}

}










