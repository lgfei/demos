package com.lgfei.demo.kafka.controller;

import com.lgfei.demo.kafka.service.SseTaskManager;
import com.lgfei.demo.kafka.service.TimeBasedConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author lgfei
 * @date 2025/10/17 16:25
 */
@RestController
@RequestMapping("/kafka")
public class TimeBasedConsumerController {

    private final SseTaskManager sseManager;
    private final TimeBasedConsumerService consumerService;

    public TimeBasedConsumerController(SseTaskManager sseManager, TimeBasedConsumerService consumerService) {
        this.sseManager = sseManager;
        this.consumerService = consumerService;
    }

    /**
     * 启动按时间消费任务
     * 示例: GET /kafka/consume?topic=mytopic&timestamp=1734432000000&duration=10000
     */
    @GetMapping("/consume")
    public SseEmitter consume(@RequestParam String topic,
                              @RequestParam String start,
                              @RequestParam String end,
                              @RequestParam(defaultValue = "10000") long duration) {

        String taskId = UUID.randomUUID().toString();
        SseEmitter emitter = sseManager.createEmitter(taskId);

        // 异步消费
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zone = ZoneId.systemDefault();
        long startTime = LocalDateTime.parse(start, fmt)
                .atZone(zone)
                .toInstant()
                .toEpochMilli();
        consumerService.consumeFromTime(taskId, topic, startTime, duration);

        return emitter;
    }
}
