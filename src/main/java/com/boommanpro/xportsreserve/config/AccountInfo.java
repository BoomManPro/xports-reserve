package com.boommanpro.xportsreserve.config;

import com.boommanpro.xportsreserve.model.ReserveRequire;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Slf4j
@RefreshScope
@ConfigurationProperties(prefix = "xports.venues.account")
public class AccountInfo implements InitializingBean {

    private String cookie;

    private String requestUserAgent;

    private List<ReserveRequire> requires;

    private String sendKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("accountInfo refresh set:{}", this);
    }

    public Set<String> getRequireTimeKey(String targetDate) {
        return requires.stream()
                .filter(require -> require.getTargetDate().equals(targetDate) && require.getReserved())
                .map(require -> String.format("%s-%s", require.getStartHour(), require.getEndHour()))
                .collect(Collectors.toSet());
    }

}
