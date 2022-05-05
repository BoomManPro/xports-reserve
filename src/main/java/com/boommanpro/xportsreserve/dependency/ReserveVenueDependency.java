package com.boommanpro.xportsreserve.dependency;

import com.boommanpro.xportsreserve.model.AsyncResult;
import com.boommanpro.xportsreserve.model.CommitInfo;
import com.boommanpro.xportsreserve.model.CommitResult;
import com.boommanpro.xportsreserve.model.VenuePage;
import com.boommanpro.xportsreserve.rest.VenueReserveClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class ReserveVenueDependency {


    @Autowired
    private VenueReserveClient venueReserveClient;


    @HystrixCommand
    public Future<CommitResult> asyncCommit(CommitInfo commitInfo, String account) {
        return AsyncResult.build(() -> venueReserveClient.commit(commitInfo, account));
    }


    @HystrixCommand
    public Future<VenuePage> asyncQueryPage(String venueSite, String queryDate, String account) {
        return AsyncResult.build(() -> venueReserveClient.queryPage(venueSite, queryDate, account));
    }
}
