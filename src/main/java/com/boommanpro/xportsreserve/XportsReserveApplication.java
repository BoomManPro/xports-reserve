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
        SpringApplication.run(XportsReserveApplication.class, args);
        log.info("Application Start Success");
    }

}
