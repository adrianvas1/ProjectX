package com.endava.app;

import com.endava.config.ServiceConfiguration;
import com.endava.config.SpringConfig;
import com.endava.health.DatabaseHealthCheck;
import com.endava.resource.StudentResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Service<ServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("Project X");
    }

    @Override
    public void run(ServiceConfiguration conf, Environment env) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // new resource

        StudentResource studentResource = (StudentResource) context.getBean("studentResource");
        env.addResource(studentResource);
        // added health check
        DatabaseHealthCheck healthCheck = (DatabaseHealthCheck) context.getBean("databaseHealthCheck");
        env.addHealthCheck(healthCheck);
    }
}
