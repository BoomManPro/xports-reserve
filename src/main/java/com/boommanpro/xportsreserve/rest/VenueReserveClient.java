package com.boommanpro.xportsreserve.rest;

import com.boommanpro.xportsreserve.model.CommitInfo;
import com.boommanpro.xportsreserve.model.CommitResult;
import com.boommanpro.xportsreserve.model.VenuePage;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface VenueReserveClient {


    @Headers("cookie: {account}")
    @RequestLine("GET /aisports-weixin/court/ajax/1101000301/1002/{venueSite}/{queryDate}")
    VenuePage queryPage(@Param String venueSite, @Param String queryDate, @Param String account);

    @Headers({"cookie: {account}", "Content-Type: application/json;charset=UTF-8"})
    @RequestLine("POST /aisports-weixin/court/commit")
    CommitResult commit(CommitInfo commitInfo, @Param String account);

}
