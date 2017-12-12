package com.example.demo.configurer;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * @Author: rogue
 * @Description: redis缓存配置类
 * @Package: com.example.demo.configurer
 * @Date: 2017/12/12
 * @Time: 10:18
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport{

    /**
     * @Author: rogue
     * @Description: 自定义生成key规则
     * @ClassName: RedisConfiguration
     * @Date: 2017/12/12
     * @Time: 10:37
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                //格式化缓存key字符串
                StringBuilder stringBuilder = new StringBuilder();
                //追加类名
                stringBuilder.append(o.getClass().getName());
                //追加方法名
                stringBuilder.append(method.getName());
                //遍历参数并且追加
                for (Object obj : objects){
                    stringBuilder.append(obj.toString());
                }
                System.out.println("调用Redis缓存Key：" + stringBuilder.toString());
                return stringBuilder.toString();
            }
        };
    }

    /**
     * @Author: rogue
     * @Description: 采用RedisCacheManager作为缓存管理器
     * @ClassName: RedisConfiguration
     * @Date: 2017/12/12
     * @Time: 10:23
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        return new RedisCacheManager(redisTemplate);
    }
}
