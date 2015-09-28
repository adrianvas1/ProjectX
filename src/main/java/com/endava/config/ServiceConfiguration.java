package com.endava.config;

import com.yammer.dropwizard.config.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ServiceConfiguration extends Configuration {

    @Valid
    private ConfigFile config;

    public ConfigFile getConfig() {
        return config;
    }

    public void setConfig(ConfigFile config) {
        this.config = config;
    }
}