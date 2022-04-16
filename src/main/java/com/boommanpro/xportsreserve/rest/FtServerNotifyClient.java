package com.boommanpro.xportsreserve.rest;

import com.boommanpro.xportsreserve.model.FtServerBody;
import com.boommanpro.xportsreserve.model.FtServerResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FtServerNotifyClient {


    @RequestLine("POST {sendKey}.send")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
    FtServerResult send(@Param String sendKey, FtServerBody ftServerBody);
}
