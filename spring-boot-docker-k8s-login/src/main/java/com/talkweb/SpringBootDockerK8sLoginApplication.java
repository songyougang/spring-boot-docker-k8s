package com.talkweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootDockerK8sLoginApplication {

    private final RestTemplateBuilder builder;

    @Autowired
    public SpringBootDockerK8sLoginApplication(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    // 使用RestTemplateBuilder来实例化RestTemplate对象
    // spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDockerK8sLoginApplication.class, args);
    }
}
