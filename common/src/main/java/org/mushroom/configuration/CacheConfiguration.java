package org.mushroom.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

@Data
@Configuration
@ConfigurationProperties(prefix = "caffeine")
@PropertySource(value = "classpath:application.yml")
public class CacheConfiguration {

    private Integer initialCapacity;

    private Integer maximumCapacity;

    private Integer expireAfterAccessSeconds;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager =
                new CaffeineCacheManager(
                        "terminals"
                        , "users"
                        , "services");
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maximumCapacity)
                .expireAfterAccess(expireAfterAccessSeconds, TimeUnit.SECONDS)
                .weakKeys();
    }
}
