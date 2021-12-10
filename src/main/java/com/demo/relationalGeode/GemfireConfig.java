package com.demo.relationalGeode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.ClientCacheConfigurer;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.ConnectionEndpoint;
import org.springframework.geode.config.annotation.EnableClusterAware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@EnableClusterAware
@EnableEntityDefinedRegions(basePackageClasses = {SomeObject.class})
@EnableGemfireRepositories(basePackageClasses = {SomeObjectRepository.class})
@EnableCachingDefinedRegions
@Configuration
public class GemfireConfig {

    @Bean
    @ConditionalOnProperty(prefix = "geode", name = "host")
    public ClientCacheConfigurer clientCacheConfigurer(@Value("${geode.host}") String host, @Value("${geode.port}") Integer port) {
        return ((beanName, bean) -> bean.setServers(Collections.singletonList(new ConnectionEndpoint(host, port))));
    }


    @Bean
    ApplicationListener<ApplicationReadyEvent> initializeCache(SomeObjectRepository repository) {
        return event -> {
            repository.save(SomeObject.builder()
                    .someId("id1")
                    .customFirstObjects(new ArrayList<>(Collections.singletonList(CustomFirstObject.builder()
                            .id(11)
                            .amount(2.0)
                            .build())))
                    .build());
        };
    }
}
