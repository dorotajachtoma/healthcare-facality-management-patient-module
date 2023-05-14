package com.djachtoma.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("healthcare-facility-management-redis")
public class ConnectionProperties {

    private String host;
    private Integer port;
    private Integer timeout;


}
