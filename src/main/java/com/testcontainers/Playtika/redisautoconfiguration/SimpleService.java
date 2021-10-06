package com.testcontainers.Playtika.redisautoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames={SimpleService.CACHE_NAME})
public class SimpleService {

    public static final String CACHE_NAME = "cache-simple";

    @Cacheable(key = "#id", unless = "#result == null")
    public String simpleMethod(String id) {
        log.info("Not in cache");
        return String.format("Your ID %s", id);
    }
}
