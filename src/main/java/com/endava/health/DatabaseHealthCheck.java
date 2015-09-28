package com.endava.health;

import com.mongodb.DB;
import com.yammer.metrics.core.HealthCheck;
import org.jongo.Jongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthCheck extends HealthCheck {
    private final DB database;

    @Autowired
    Jongo jongo;

    @Autowired
    public DatabaseHealthCheck(DB database) {
        super("mydb");
        this.database = database;
    }

    @Override
    public Result check() throws Exception {
        if (jongo.getCollection("test2") != null) {
            return Result.healthy(database.getName() + ": Database connected!");
        } else return Result.unhealthy("check db connection");
    }
}