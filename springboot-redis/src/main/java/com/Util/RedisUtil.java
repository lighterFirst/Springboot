package com.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate;
    @Transactional(rollbackFor = Exception.class)
    public  Object deSecKill(String id, String priodId)throws IOException{
        //非空判断
        if(id == null || priodId == null){
            return false;
        }

        Jedis jedis = new Jedis("192.168.26.140",6379);
        jedis.auth("redis123456");
        //秒杀
        //1连接redis
        //库存key(priodId)
        String bushKey = priodId;
        //秒杀成功用户key
        String userId = "success";
        jedis.watch(bushKey);
        redisTemplate.watch(bushKey);
        //获取库存，如果库存为空，秒杀还没开始
        String  bush =jedis.get(bushKey);
        //开启乐观锁,绑定线程

        if(null==bush){
            return new RuntimeException("秒杀还没有开始,仓库为空");
        }
        //5判断用户是否重复秒杀,第一个参数秒杀成功的用户,id为传进来的参数
        //Boolean ok = jedis.sismember(userId,id);
        Boolean ok = redisTemplate.opsForSet().isMember(userId,id);
            if(ok){
                return new RuntimeException("您已经秒杀成功，不可以秒杀了");
            }

        //6判断库存商品数量小于1，秒杀结束
        if((Integer.parseInt(String.valueOf(bush)))<1){
            System.out.println("秒杀结束");
             return new RuntimeException("秒杀结束");
        }

        //开启事务
         Transaction t1 = jedis.multi();
        redisTemplate.multi();
        //获取库存的数量的key键，并让他-1
        t1.decr(bushKey);

        //添加秒杀成功用户名单
       t1.sadd(userId,id);
       List<Object> l1 = t1.exec();
        if(l1 == null || l1 .size()==0){
            System.out.println("秒杀失败了");
            return false;
        }
        System.out.println("秒杀成功了");
        return true;
    }

}
