package com.nm.patient.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("configs")
@RefreshScope
public class ApplicationPropertiesConfiguration
{
    private int testConstante;

    public int getTestConstante()
    {
        return testConstante;
    }

    public void setTestConstante(int testConstante)
    {
        this.testConstante = testConstante;
    }
}
