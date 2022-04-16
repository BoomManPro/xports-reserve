package com.boommanpro.xportsreserve.model;

import lombok.Data;
import me.ghui.fruit.annotations.Pick;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class VenuePage {

    @Pick("head > title")
    private String title;

    @Pick("body > div.inner-fixed.inner-fixed-top > div.select-type > div > div > label.sort.checked > span")
    private String name;

    @Pick("body > div.reserve-ground > div.scroll-field > div > div.field-list > div > div > span")
    private List<VenuesInfo> venuesInfoList;

    private String venueSite;

    public boolean hasContent() {
        return !parserError();
    }

    public boolean parserError() {
        return StringUtils.hasText(title) && "失败".equals(title);
    }
}
