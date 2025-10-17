package com.lgfei.demo.ksqldb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;


/**
 * @author lgfei
 * @date 2025/10/15 10:53
 */
@Configuration
public class KsqlDbConfig {

    @Value("${ksqldb.server-url}")
    private String ksqlDbServerUrl;

    @Bean
    public HttpClient httpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public String ksqlDbServerUrl() {
        return ksqlDbServerUrl;
    }
}
