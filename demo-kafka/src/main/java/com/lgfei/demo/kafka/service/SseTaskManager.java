package com.lgfei.demo.kafka.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lgfei
 * @date 2025/10/17 16:59
 */
@Component
public class SseTaskManager {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(String taskId) {
        SseEmitter emitter = new SseEmitter(0L); // 不超时
        emitters.put(taskId, emitter);

        emitter.onCompletion(() -> emitters.remove(taskId));
        emitter.onTimeout(() -> emitters.remove(taskId));

        return emitter;
    }

    public void send(String taskId, String msg) {
        SseEmitter emitter = emitters.get(taskId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("message").data(msg, MediaType.TEXT_PLAIN));
            } catch (IOException e) {
                emitters.remove(taskId);
            }
        }
    }

    public void complete(String taskId, String msg) {
        SseEmitter emitter = emitters.remove(taskId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("complete").data(msg, MediaType.TEXT_PLAIN));
                emitter.complete();
            } catch (IOException ignored) {}
        }
    }
}
