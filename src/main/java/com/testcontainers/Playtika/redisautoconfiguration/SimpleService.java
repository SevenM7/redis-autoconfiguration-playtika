package com.testcontainers.Playtika.redisautoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames={"cache-simple"})
public class SimpleService {

    @Cacheable(key = "#id", unless = "#result == null")
    public String simpleMethod(String id) {
        log.info("Not in cache");
        return String.format("Your ID %s", id);
    }
}
