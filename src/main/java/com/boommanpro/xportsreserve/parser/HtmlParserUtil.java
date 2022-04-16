package com.boommanpro.xportsreserve.parser;

import me.ghui.fruit.Fruit;

import java.lang.reflect.Type;

public class HtmlParserUtil {

    public static <T> T parserFromHtml(String html, Class<T> clazz) {
        return new Fruit().fromHtml(html, clazz);
    }

    public static <T> T parserFromHtml(String html, Type type) {
        return new Fruit().fromHtml(html, type);
    }
}
