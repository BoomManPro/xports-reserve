package com.boommanpro.xportsreserve.cron;

import com.boommanpro.xportsreserve.config.AccountInfo;
import com.boommanpro.xportsreserve.model.CommitResult;
import com.boommanpro.xportsreserve.service.FtNotifyAccountService;
import com.boommanpro.xportsreserve.service.VenueReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class VenueCronTask {

    @Autowired
    private VenueReserveService venueReserveService;

    @Autowired
    private AccountInfo accountInfo;

    @Autowired
    private FtNotifyAccountService notifyWxAccountService;

    @Scheduled(cron = "${xports.venues.refresh-cookie}")
    public void scheduleRefreshCookie() throws ExecutionException, InterruptedException {
        if (accountInfo.isCookieStatus()) {
            boolean status = venueReserveService.refreshSession(accountInfo);
            accountInfo.setCookieStatus(status);
            if (!status) {
                notifyWxAccountService.notifyRefreshSessionError(accountInfo.getSendKey());
            }

        }
    }


    @Scheduled(cron = "${xports.venues.reserve}")
    public void scheduleReserve() {
        String nowDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (accountInfo.getRequires().stream().anyMatch(require -> require.getTargetDate().equals(nowDate))) {
            List<CommitResult> commitResults = venueReserveService.reserveVenue(accountInfo, nowDate);
            notifyWxAccountService.notifyReserveStatus(accountInfo.getSendKey(), commitResults);
        }
    }

}
