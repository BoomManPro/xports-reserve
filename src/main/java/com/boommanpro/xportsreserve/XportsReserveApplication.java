package com.boommanpro.xportsreserve;

import com.boommanpro.xportsreserve.config.AccountInfo;
import com.boommanpro.xportsreserve.config.VenuePageConfigProperties;
import com.boommanpro.xportsreserve.config.XxlJobConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@Slf4j
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(value = {VenuePageConfigProperties.class, AccountInfo.class, XxlJobConfigProperties.class})
public class XportsReserveApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(XportsReserveApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = Optional.ofNullable(environment.getProperty("server.port")).orElse("8080");
        String contextPath = Optional.ofNullable(environment.getProperty("servlet.context-path")).orElse("/");
        log.info("Server Start Success , Link To Open : http://127.0.0.1:{}{}", port, contextPath);
    }

}
