package com.boommanpro.xportsreserve.config;

import com.boommanpro.xportsreserve.service.FtNotifyAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRefreshEventListener implements ApplicationListener<RefreshScopeRefreshedEvent> {

    private final FtNotifyAccountService ftNotifyAccountService;

    private final AccountInfo accountInfo;

    public AccountRefreshEventListener(AccountInfo accountInfo, FtNotifyAccountService ftNotifyAccountService) {
        this.accountInfo = accountInfo;
        this.ftNotifyAccountService = ftNotifyAccountService;
    }


    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent event) {
        ftNotifyAccountService.notifyMessage(accountInfo.getSendKey(), "刷新Cookie成功", "");
    }
}
