package com.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemon1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.26.135",6379);
        jedis.auth("redis123456");
        String s1 = jedis.ping();
        System.out.println("s1 = " + s1);
    }

    @Test
    public void test01(){
        Jedis jedis = new Jedis("192.168.26.135",6379);
        jedis.auth("redis123456");
        jedis.set("1","李四");
        jedis.mset("2","张三","3","王五");
        //从右边添加
        jedis.lpush("4","牡丹","玫瑰");
        //取出添加的右边元素
        List l1 = jedis.lrange("4",0,-1);
        System.out.println(l1);
        //set集合批量添加
        jedis.sadd("5","华为","小米");
       jedis.smembers("5");
        Set<String> set = jedis.keys("5");
        System.out.println("set"+set);
        for(String s1:set){
            System.out.println("s1 = " + s1);
            if(Integer.parseInt(s1) <4) {
                System.out.println("value" + jedis.get(s1));
            }
            System.out.println(jedis.ttl("s1"));
        }
    }

    //hash表  还有mset，mget多次设置值
    @Test
    public void test02(){
        Jedis jedis = new Jedis("192.168.26.135",6379);
        jedis.auth("redis123456");
        jedis.hset("6","age","20");
        String hget = jedis.hget("6","age");
        System.out.println(hget);
    }
    //zset
    @Test
    public void test03(){
        Jedis jedis = new Jedis("192.168.26.135",6379);
        jedis.auth("redis123456");
        jedis.zadd("china",100d,"上海");
        List s1 = jedis.zrange("china",0,-1);
        System.out.println(s1);
    }

}
