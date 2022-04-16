package com.boommanpro.xportsreserve.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobConfigProperties {

    private String adminAddresses;

    private String accessToken;

    private String appname;

    private String address;

    private String ip;

    private Integer port;

    private String logPath;

    private Integer logRetentionDays;

    private Boolean enable;
}
