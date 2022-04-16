package com.boommanpro.xportsreserve.rest.config;

import com.boommanpro.xportsreserve.config.AccountInfo;
import com.boommanpro.xportsreserve.rest.FtServerNotifyClient;
import com.boommanpro.xportsreserve.rest.VenueReserveClient;
import feign.*;
import feign.form.FormEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class RestConfiguration {

    @Autowired
    private AccountInfo accountInfo;

    private static final String SERVER = "https://webssl.xports.cn";
    private static final String FT_SERVER = "https://sctapi.ftqq.com/";


    @Bean
    public VenueReserveClient venueReserveClient() throws Exception {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new AllPowerDecoder())
                .requestInterceptor(requestTemplate -> {
                    requestTemplate.header("user-agent", accountInfo.getRequestUserAgent());
                    requestTemplate.header("host", "webssl.xports.cn");
                })
                .client(buildHttpClient())
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(3, TimeUnit.MINUTES, 3, TimeUnit.MINUTES, true))
                .target(VenueReserveClient.class, SERVER);
    }

    @Bean
    public FtServerNotifyClient wxServerNotifyClient() throws Exception {
        return Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder())
                .client(buildHttpClient())
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(3, TimeUnit.MINUTES, 3, TimeUnit.MINUTES, true))
                .target(FtServerNotifyClient.class, FT_SERVER);
    }

    private Client buildHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        return new ApacheHttpClient(HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build());
    }

}
