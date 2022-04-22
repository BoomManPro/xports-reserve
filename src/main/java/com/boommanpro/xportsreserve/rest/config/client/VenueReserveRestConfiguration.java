package com.boommanpro.xportsreserve.rest.config.client;

import com.boommanpro.xportsreserve.config.AccountInfo;
import com.boommanpro.xportsreserve.rest.VenueReserveClient;
import com.boommanpro.xportsreserve.rest.config.decoder.AllPowerDecoder;
import com.boommanpro.xportsreserve.utils.HttpClientFactory;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class VenueReserveRestConfiguration {

    private final AccountInfo accountInfo;

    public VenueReserveRestConfiguration(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }


    public interface VenueReserveConstant {
        String SERVER_URL = "https://webssl.xports.cn";
        String HOST = "webssl.xports.cn";

    }

    @Bean
    public VenueReserveClient venueReserveClient() throws Exception {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new AllPowerDecoder())
                .requestInterceptor(requestTemplate -> {
                    requestTemplate.header(HTTP.USER_AGENT, accountInfo.getRequestUserAgent());
                    requestTemplate.header(HTTP.TARGET_HOST, VenueReserveConstant.HOST);
                })
                .client(HttpClientFactory.buildIgnoreSSLClient())
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(3, TimeUnit.MINUTES, 3, TimeUnit.MINUTES, true))
                .target(VenueReserveClient.class, VenueReserveConstant.SERVER_URL);
    }


}
