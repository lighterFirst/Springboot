package com.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

@Component
    public class RidesConfig  {

    @Autowired
    RedisProperties redisProperties;

    @Value("${spring.jedis.pool.max-idle:25}")
    private int maxIdle;
    @Value("${spring.jedis.pool.min-idle:10}")
    private int minIdle;
    @Value("${spring.jedis.pool.max-active:25}")
    private int maxActive;
    @Value("${spring.jedis.pool.max-wait:-1}")
    private int maxWait;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        if (!StringUtils.isEmpty(redisProperties.getPassword())) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        return redisStandaloneConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(
            JedisPoolConfig jedisPoolConfig, RedisStandaloneConfiguration redisStandaloneConfiguration){
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(redisProperties.getTimeout());
        jedisClientConfiguration.usePooling();
        jedisClientConfiguration.clientName("test");
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        builder.poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,builder.build());
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory redisConnectionFactory){
        //spring默认自动注入了LettuceConnectionFactory,我们可以直接获取,或自己创建JedisConnectionFactory
//        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }


}








