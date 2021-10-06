package com.testcontainers.Playtika.redisautoconfiguration

import org.spockframework.spring.SpringSpy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification
import spock.lang.Unroll

import static com.testcontainers.Playtika.redisautoconfiguration.SimpleService.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest(classes = [RedisAutoconfigurationApplicationTests.class])
@AutoConfigureMockMvc
class TestCacheSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private CacheManager cacheManager

    @Unroll("test with id: #id")
    def "simple test"() {
        setup:
        def executeGet = {
            mvc.perform(get("/$id"))
        }

        expect:
        cacheManager.getCache(CACHE_NAME).get(id) == null

        when:
        executeGet()

        then:
        cacheManager.getCache(CACHE_NAME).get(id, String) == "Your ID $id"

        when:
        cacheManager.getCache(CACHE_NAME).put(id, "Your ID is $id" as String)
        def get = executeGet()

        then:
        get.andReturn().response.getContentAsString() == "Your ID is $id"

        where:
        id << ['id-1', 'id-2', 'id-3']
    }
}