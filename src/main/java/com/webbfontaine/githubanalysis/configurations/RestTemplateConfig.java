package com.webbfontaine.githubanalysis.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Value("${github.url}")
    private String githubUrl;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
