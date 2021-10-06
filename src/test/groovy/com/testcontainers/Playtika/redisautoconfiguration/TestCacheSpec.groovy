package com.testcontainers.Playtika.redisautoconfiguration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest(classes = [RedisAutoconfigurationApplicationTests.class])
@AutoConfigureMockMvc
class TestCacheSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @SpyBean
    SimpleService simpleService

    @Unroll("test with id: #id")
    def "simple test"() {
        setup:
        def executeGet = {
            mvc.perform(get("/$id"))
        }
        when:
        executeGet()

        then:
        1 * simpleService.simpleMethod(_)

        when:
        executeGet()

        then:
        0 * simpleService.simpleMethod(_)

        where:
        id << ['id-1', 'id-2', 'id-3']
    }
}