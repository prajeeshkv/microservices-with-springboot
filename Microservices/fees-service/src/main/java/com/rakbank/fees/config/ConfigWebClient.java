package com.rakbank.fees.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    @Value("${student.service.url.base}")
    private String studentServiceBaseUrl;

    @Bean
    public WebClient.Builder studentWebClient() {
        return WebClient.builder()
                .baseUrl(studentServiceBaseUrl);
    }
}
