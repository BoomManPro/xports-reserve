package com.boommanpro.xportsreserve.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "xxl.job", name = "enable", havingValue = "true", matchIfMissing = false)
public class XxlJobConfiguration {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobConfigProperties xxlJobConfigProperties) {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobConfigProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(xxlJobConfigProperties.getAppname());
        xxlJobSpringExecutor.setAddress(xxlJobConfigProperties.getAddress());
        xxlJobSpringExecutor.setIp(xxlJobConfigProperties.getIp());
        xxlJobSpringExecutor.setPort(xxlJobConfigProperties.getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobConfigProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobConfigProperties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobConfigProperties.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
