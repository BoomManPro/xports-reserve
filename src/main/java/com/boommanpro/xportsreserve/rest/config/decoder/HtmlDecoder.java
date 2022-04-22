package com.boommanpro.xportsreserve.rest.config.decoder;

import com.boommanpro.xportsreserve.parser.HtmlParserUtil;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class HtmlDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Response.Body body = response.body();
        if (body == null) {
            return null;
        }
        return HtmlParserUtil.parserFromHtml(Util.toString(body.asReader(Util.UTF_8)), type);
    }
}
