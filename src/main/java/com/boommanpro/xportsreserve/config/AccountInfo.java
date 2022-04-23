package com.boommanpro.xportsreserve.config;

import com.boommanpro.xportsreserve.model.ReserveRequire;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
@RefreshScope
@ConfigurationProperties(prefix = "xports.venues.account")
public class AccountInfo implements InitializingBean {

    private String cookie;

    private boolean cookieStatus;

    private String requestUserAgent;

    private List<ReserveRequire> requires;

    private String sendKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("accountInfo refresh set:{}", this);
        cookieStatus = true;
    }

    public List<String> getTargetDateRequireTimeKey(String targetDate) {
        if (CollectionUtils.isEmpty(requires) || !StringUtils.hasText(targetDate)) {
            log.error("targetDate or requires is empty, targetDate:{}, requires:{}", targetDate, requires);
            return new ArrayList<>();
        }
        return requires.stream()
                .filter(require -> targetDate.equals(require.getTargetDate()) && !require.isReserved())
                .map(require -> String.format("%s-%s", require.getStartHour(), require.getEndHour()))
                .collect(Collectors.toList());
    }

}
