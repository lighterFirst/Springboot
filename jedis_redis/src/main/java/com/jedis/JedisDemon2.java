package com.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class JedisDemon2 {
    public static void main(String[] args) {
       test01("123143234");
   //getRedisKey("123143234","559448");
    }




    public static void test01(String phone) {
        Jedis jedis = new Jedis("192.168.26.135",6379);
        jedis.auth("redis123456");
        //设置手机发送次数的countkey
        String countkey = "phone"+phone+":code";
        //设置手机验证码codekey
        String codeKey = "countKey"+phone+":count";
        //i是手机发送次数
        String i = jedis.get(countkey);
        if(i==null){
            jedis.setex(countkey,24*60*60,"1");
        }else if(Integer.parseInt(i)<3){
            //设置手机发送次数
            jedis.incr(countkey);
        }else if(Integer.parseInt(i)>2){
            jedis.close();
            throw new RuntimeException("以超过三次");
        }
        Object code1 =getRandomNum();
        //设置验证码
        jedis.setex(codeKey,120,String.valueOf(code1));
    }

    public static void getRedisKey(String phone,String code){
        Jedis jedis = new Jedis("192.168.26.135",6379);
        jedis.auth("redis123456");
        String codeKey = "countKey"+phone+":count";
        String redisCode = jedis.get(codeKey);
        if (code.equals(redisCode)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        jedis.close();
    }

    public static Object getRandomNum(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<6;i++){
            int k =random.nextInt(10);
            stringBuilder.append(k);
        }
        return stringBuilder;
    }
}
