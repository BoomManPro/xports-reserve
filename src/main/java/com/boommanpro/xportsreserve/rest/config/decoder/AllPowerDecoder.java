package com.boommanpro.xportsreserve.rest.config.decoder;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class AllPowerDecoder implements Decoder {

    private final JacksonDecoder jacksonDecoder = new JacksonDecoder();

    private final HtmlDecoder htmlDecoder = new HtmlDecoder();

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.headers().get("Content-Type").stream().anyMatch(s -> s.contains("text/html"))) {
            return htmlDecoder.decode(response, type);
        }
        return jacksonDecoder.decode(response, type);
    }
}
