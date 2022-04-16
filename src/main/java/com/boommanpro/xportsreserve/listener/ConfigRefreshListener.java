package com.boommanpro.xportsreserve.listener;

import com.boommanpro.xportsreserve.global.GlobalAccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfigRefreshListener implements ApplicationListener<RefreshScopeRefreshedEvent> {

    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent event) {
        log.info("refresh event:{}", event);
        GlobalAccountStatus.accountStatus = true;
    }
}
