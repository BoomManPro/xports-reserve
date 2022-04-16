package com.boommanpro.xportsreserve.service;

import com.alibaba.fastjson.JSON;
import com.boommanpro.xportsreserve.model.CommitResult;
import com.boommanpro.xportsreserve.model.FtServerBody;
import com.boommanpro.xportsreserve.model.FtServerResult;
import com.boommanpro.xportsreserve.rest.FtServerNotifyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FtNotifyAccountService {

    @Autowired
    private FtServerNotifyClient ftServerNotifyClient;

    public void notifyRefreshSessionError(String sendKey) {
        FtServerResult result = ftServerNotifyClient.send(sendKey, new FtServerBody("refreshSessionError", "手动登录nacos刷新"));
        log.info("notify result:{}", result);
    }

    public void notifyReserveStatus(String sendKey, List<CommitResult> commitResults) {
        FtServerResult result = ftServerNotifyClient.send(sendKey, new FtServerBody("预定成功，尽快付款", JSON.toJSONString(commitResults)));
        log.info("notify result:{}", result);
    }
}
