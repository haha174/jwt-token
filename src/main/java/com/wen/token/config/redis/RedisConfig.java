package com.wen.token.config.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Autowired
    private RedisConfigProperties redis;
    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {     ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲接连
        jedisPoolConfig.setMaxIdle(redis.getMaxIdle());
        //最小空闲连接
        jedisPoolConfig.setMinIdle(redis.getMinIdle());
        //连接池最大阻塞等待时间
        jedisPoolConfig.setMaxWaitMillis(redis.getMaxWait());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        //主机地址
        jedisConnectionFactory.setHostName(redis.getHost());
        //端口
        jedisConnectionFactory.setPort(redis.getPort());
        //密码
        jedisConnectionFactory.setPassword(redis.getPassword());
        //索引
        jedisConnectionFactory.setDatabase(redis.getDatabase());
        //超时时间
        jedisConnectionFactory.setTimeout(redis.getTimeOut());
        jedisConnectionFactory.setUsePool(true);    
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);        redisTemplate.setConnectionFactory(redisConnectionFactory());
        //redis   开启事务
        redisTemplate.setEnableTransactionSupport(true);
        //hash  使用jdk  的序列化
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer/*new JdkSerializationRedisSerializer()*/);
        //StringRedisSerializer  key  序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //keySerializer  对key的默认序列化器。默认值是StringSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //  valueSerializer
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}