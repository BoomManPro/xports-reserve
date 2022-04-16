package com.boommanpro.xportsreserve.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

@Data
@Slf4j
@RefreshScope
@ConfigurationProperties(prefix = "xports.venues.pages")
public class VenuePageConfigProperties implements InitializingBean {

    private List<String> venueSite;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("venuePageConfig refresh set:{}", this);
    }
}
