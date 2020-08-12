package com.xiaoxin.demo;

import com.xiaoxin.demo.util.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.xiaoxin.demo.search")
@EnableJpaRepositories(basePackages = {"com.xiaoxin.demo.dao", "com.xiaoxin.demo.pojo"})
public class Application {
    static {
        PortUtil.checkPort(6379, "Redis 服务端", true);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
