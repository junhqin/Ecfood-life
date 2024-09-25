package com.ecf.config;

import com.ecf.utils.RedisProperty;
import io.lettuce.core.ScriptOutputType;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
public class RedissionConfig {
    @Resource
    private RedisProperty redisProperty;
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String redisAddress = String.format("redis://%s:%d", redisProperty.getHost(), redisProperty.getPort());
        System.out.println("password = " + redisProperty.getPassword());
        config.useSingleServer()
                .setAddress(redisAddress)
                .setPassword(redisProperty.getPassword());
        return Redisson.create(config);
    }
}
