package com.lgfei.demo.ksqldb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author lgfei
 * @date 2025/10/15 10:56
 */
@Service
public class KsqlDbService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String ksqlDbServerUrl;

    public KsqlDbService(String ksqlDbServerUrl){
        this.ksqlDbServerUrl = ksqlDbServerUrl;
    }

    /** 查询 Topic 历史数据 */
    public String queryTopicData(String topicName, int limit) throws Exception {
        ensureStreamExists(topicName);
        String streamName = topicName + "_stream";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zone = ZoneId.systemDefault();
        long startTime = LocalDateTime.parse("2025-10-16 11:10:00", fmt)
                .atZone(zone)
                .toInstant()
                .toEpochMilli();
        long endTime = LocalDateTime.parse("2025-10-16 11:20:00", fmt)
                .atZone(zone)
                .toInstant()
                .toEpochMilli();
        //String sql = String.format("SELECT * FROM %s WHERE `es` BETWEEN %d AND %d;", streamName, startTime, endTime);
        String sql = String.format("SELECT `ROWPARTITION`,`ROWOFFSET`,* FROM %s WHERE `es` BETWEEN %d AND %d EMIT CHANGES;", streamName, startTime, endTime);
        System.out.println(sql);
        executeQueryStream(sql);
        //executeQuery(sql);
        return "ok";
    }

    /** 确保 Stream 存在 */
    private void ensureStreamExists(String topicName) throws Exception {
        String showStreamsSql = "SHOW STREAMS;";
        JsonNode streams = executeKsql(showStreamsSql);
        String[] streamNames = {topicName + "_stream"};
        for (String streamName : streamNames) {
            boolean exists = streams.toString().contains(streamName.toUpperCase());
            if (exists) {
                String deleteStreamSql = String.format("DROP STREAM IF EXISTS %s;", streamName);
                //executeKsql(deleteStreamSql);
            }else{
                String createStreamSql = String.format(
                        "CREATE STREAM %s (" +
                                "`id` BIGINT," +
                                "`database` STRING," +
                                "`table` STRING, " +
                                "`pkNames` STRING, " +
                                "`isDdl` BOOLEAN," +
                                "`type` STRING," +
                                "`es` BIGINT," +
                                "`ts` BIGINT," +
                                "`sql` STRING," +
                                "`sqlType` MAP<STRING,INT>," +
                                "`mysqlType` MAP<STRING,STRING>," +
                                "`old` STRING, " +
                                "`data` STRING) WITH (KAFKA_TOPIC='%s', VALUE_FORMAT='JSON');",
                        streamName, topicName);
                executeKsql(createStreamSql);
                String describeStreamSql = String.format("DESCRIBE %s;", streamName);
                JsonNode stream = executeKsql(describeStreamSql);
                System.out.println(stream);
            }
        }
    }

    /** 执行 ksql 命令（DDL/DML） */
    private JsonNode executeKsql(String sql) throws Exception {
        System.out.println(sql);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(ksqlDbServerUrl + "/ksql");
            String body = String.format("{\"ksql\": \"%s\"}", sql);
            post.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
            post.setHeader("Content-Type", "application/vnd.ksql.v1+json");

            try (CloseableHttpResponse response = client.execute(post)) {
                String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                return objectMapper.readTree(json);
            }
        }
    }

    /** 执行查询（SELECT） */
    public void executeQuery(String sql) throws Exception {
        // 配置请求
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofSeconds(10))
                .setConnectionRequestTimeout(Timeout.ofSeconds(10))
                .setResponseTimeout(Timeout.ofMinutes(5))  // 非流式，给足够时间即可
                .build();

        try (CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build()) {

            HttpPost post = new HttpPost(ksqlDbServerUrl + "/query");
            post.setEntity(new StringEntity("{\"ksql\": \"" + sql + "\",\"streamsProperties\":{\"auto.offset.reset\":\"earliest\"}}", StandardCharsets.UTF_8));
            post.setHeader("Content-Type", "application/vnd.ksql.v1+json");

            try (CloseableHttpResponse response = client.execute(post)) {
                String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JsonNode root = objectMapper.readTree(json);
                System.out.println(root);
            }
        }
    }

    /** 流式查询 **/
    private void executeQueryStream(String sql) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(Timeout.DISABLED)       // 永不超时，适合流式查询
                .setConnectTimeout(Timeout.of(10, TimeUnit.SECONDS)) // 建立连接超时
                .setConnectionRequestTimeout(Timeout.of(10, TimeUnit.SECONDS)) // 获取连接超时
                .build();
        try (CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build()) {
            HttpPost post = new HttpPost(ksqlDbServerUrl + "/query-stream");
            String body = String.format("{\"sql\": \"%s\",\"streamsProperties\":{\"auto.offset.reset\":\"earliest\"}}", sql);
            post.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
            post.setHeader("Content-Type", "application/vnd.ksql.v1+json");

            try (CloseableHttpResponse response = client.execute(post);
                 InputStream input = response.getEntity().getContent();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    // 每一行都是一条 JSON 记录
                    JsonNode node = objectMapper.readTree(line);
                    System.out.println(node);
                }
            }
        }
    }
}
