package com.lgfei.demo.ksqldb.controller;

import com.lgfei.demo.ksqldb.service.KsqlDbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lgfei
 * @date 2025/10/15 11:01
 */
@RestController
@RequestMapping("/ksql")
public class KsqlController {

    private final KsqlDbService ksqlDbService;

    public KsqlController(KsqlDbService ksqlDbService){
        this.ksqlDbService = ksqlDbService;
    }

    @GetMapping("/query")
    public String query(
            @RequestParam String topic,
            @RequestParam(defaultValue = "10") int limit) throws Exception {
        return ksqlDbService.queryTopicData(topic, limit);
    }
}
