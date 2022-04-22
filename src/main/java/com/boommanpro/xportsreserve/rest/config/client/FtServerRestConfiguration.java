package com.boommanpro.xportsreserve.rest.config.client;

import com.boommanpro.xportsreserve.rest.FtServerNotifyClient;
import com.boommanpro.xportsreserve.utils.HttpClientFactory;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class FtServerRestConfiguration {


    public interface FTServerConstant {
        String SERVER_URL = "https://sctapi.ftqq.com/";
    }


    @Bean
    public FtServerNotifyClient ftServerNotifyClient() throws Exception {
        return Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder())
                .client(HttpClientFactory.buildIgnoreSSLClient())
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(3, TimeUnit.MINUTES, 3, TimeUnit.MINUTES, true))
                .target(FtServerNotifyClient.class, FTServerConstant.SERVER_URL);
    }


}
