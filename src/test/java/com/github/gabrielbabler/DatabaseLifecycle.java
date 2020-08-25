package com.github.gabrielbabler;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class DatabaseLifecycle implements QuarkusTestResourceLifecycleManager {

    private static MySQLContainer<?> MYSLQ = new MySQLContainer<>("mysql:8");

    @Override
    public Map<String, String> start() {
        MYSLQ.start();
        Map<String, String> proprieties = new HashMap<>();
        proprieties.put("quarkus.datasource.url", MYSLQ.getJdbcUrl());
        proprieties.put("quarkus.datasource.username", MYSLQ.getUsername());
        proprieties.put("quarkus.datasource.password", MYSLQ.getPassword());
        return proprieties;
    }

    @Override
    public void stop() {
        if(MYSLQ != null) {
            MYSLQ.stop();
        }
    }
}
