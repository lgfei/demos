package com.lgfei.demo.kafka.service;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

/**
 * @author lgfei
 * @date 2025/10/17 16:24
 */
@Service
public class TimeBasedConsumerService {

    private final Properties consumerProps;
    private final SseTaskManager sseManager;

    public TimeBasedConsumerService(Properties kafkaConsumerProps, SseTaskManager sseManager) {
        this.consumerProps = kafkaConsumerProps;
        this.sseManager = sseManager;
    }

    @Async
    public void consumeFromTime(String taskId, String topic, long timestampMillis, long durationMillis) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps)) {
            List<PartitionInfo> partitions = consumer.partitionsFor(topic);
            List<TopicPartition> topicPartitions = new ArrayList<>();
            for (PartitionInfo p : partitions) {
                topicPartitions.add(new TopicPartition(topic, p.partition()));
            }
            consumer.assign(topicPartitions);

            // 查找每个分区时间对应 offset
            Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
            for (TopicPartition partition : topicPartitions) {
                timestampsToSearch.put(partition, timestampMillis);
            }

            Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timestampsToSearch);
            for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : offsets.entrySet()) {
                OffsetAndTimestamp oat = entry.getValue();
                if (oat != null) {
                    consumer.seek(entry.getKey(), oat.offset());
                    sseManager.send(taskId, String.format("Partition %d seek to offset %d", entry.getKey().partition(), oat.offset()));
                } else {
                    consumer.seekToEnd(Collections.singleton(entry.getKey()));
                    sseManager.send(taskId, String.format("Partition %d has no data for time, seekToEnd", entry.getKey().partition()));
                }
            }

            long endTime = System.currentTimeMillis() + durationMillis;
            while (System.currentTimeMillis() < endTime) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    String msg = String.format("[p%d, o%d, t%d] %s",
                            record.partition(), record.offset(), record.timestamp(), record.value());
                    sseManager.send(taskId, msg);
                }
            }

            sseManager.complete(taskId, "✅ 消费完成");
        } catch (Exception e) {
            sseManager.complete(taskId, "❌ 异常: " + e.getMessage());
        }
    }
}