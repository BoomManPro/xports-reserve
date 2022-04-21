package com.boommanpro.xportsreserve.service;

import com.boommanpro.xportsreserve.config.AccountInfo;
import com.boommanpro.xportsreserve.config.VenuePageConfigProperties;
import com.boommanpro.xportsreserve.dependency.ReserveVenueDependency;
import com.boommanpro.xportsreserve.model.CommitInfo;
import com.boommanpro.xportsreserve.model.CommitResult;
import com.boommanpro.xportsreserve.model.VenuePage;
import com.boommanpro.xportsreserve.model.VenuesInfo;
import com.boommanpro.xportsreserve.rest.VenueReserveClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@Service
public class VenueReserveService {

    @Autowired
    private VenuePageConfigProperties config;

    @Autowired
    private VenueReserveClient venueReserveClient;

    @Autowired
    private ReserveVenueDependency reserveVenueDependency;


    public boolean refreshSession(AccountInfo accountInfo) throws ExecutionException, InterruptedException {
        log.info("refresh Session account:{}", accountInfo);
        String nowDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        for (String venueSite : config.getVenueSite()) {
            Future<VenuePage> venuePageFuture = reserveVenueDependency.asyncQueryPage(venueSite, nowDate, accountInfo.getCookie());
            if (!venuePageFuture.get().hasContent()) {
                log.error("user refresh session error.");
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("rawtypes")
    public List<CommitResult> reserveVenue(AccountInfo accountInfo, String nowDate) {
        log.info("reserveVenue account:{}, nowDate:{}", accountInfo, nowDate);
        List<CommitResult> commitInfoList = new ArrayList<>();
        //需要预定条件
        List<String> requireDateKey = accountInfo.getTargetDateRequireTimeKey(nowDate);
        //获取预定列表Stream
        Stream<VenuesInfo> venuesInfoStream = config.getVenueSite()
                .parallelStream()
                .map(getVenuePage(accountInfo, nowDate))
                .filter(VenuePage::hasContent)
                .map(VenuePage::getVenuesInfoList)
                .flatMap(Collection::stream)
                .filter(VenuesInfo::canReserve)
                .filter(venuesInfo -> requireDateKey.contains(venuesInfo.getTimeKey()));

        //异步处理
        CompletableFuture[] completableFutures = venuesInfoStream
                .map(convert2CompletableFuture(accountInfo, nowDate, commitInfoList))
                .toArray(CompletableFuture[]::new);
        //全部处理
        CompletableFuture.allOf(completableFutures).join();
        log.info("reserveVenue result:{}", commitInfoList);
        return commitInfoList;

    }

    private Function<VenuesInfo, CompletableFuture<CommitResult>> convert2CompletableFuture(AccountInfo accountInfo, String nowDate, List<CommitResult> commitInfoList) {
        return venuesInfo -> CompletableFuture.supplyAsync(() -> {
            CommitInfo commitInfo = new CommitInfo(venuesInfo.getVenueSite(), nowDate, venuesInfo.getId());
            return venueReserveClient.commit(commitInfo, accountInfo.getCookie());
        }).whenCompleteAsync((commitResult, throwable) -> {
            if (throwable != null) {
                log.error("commit error,e", throwable);
                return;
            }
            commitInfoList.add(commitResult);
        });
    }

    private Function<String, VenuePage> getVenuePage(AccountInfo accountInfo, String nowDate) {
        return venueSite -> {
            VenuePage venuePage = venueReserveClient.queryPage(venueSite, nowDate, accountInfo.getCookie());
            venuePage.setVenueSite(venueSite);
            venuePage.getVenuesInfoList().forEach(venuesInfo -> venuesInfo.setVenueSite(venueSite));
            return venuePage;
        };
    }
}
