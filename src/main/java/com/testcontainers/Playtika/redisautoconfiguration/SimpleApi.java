package com.testcontainers.Playtika.redisautoconfiguration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleApi {

    private final SimpleService simpleService;

    public SimpleApi(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("/{id}")
    public String getID(@PathVariable("id") String id) {
        return simpleService.simpleMethod(id);
    }
}
