package com.boommanpro.xportsreserve.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

@Data
@Slf4j
@Validated
@RefreshScope
@ConfigurationProperties(prefix = "xports.venues.pages")
public class VenuePageConfigProperties implements InitializingBean {

    @NotEmpty
    private List<String> venueSite = Arrays.asList("1602", "1610");

    @Override
    public void afterPropertiesSet() {
        log.debug("venuePageConfig refresh set:{}", this);
    }
}
