package com.boommanpro.xportsreserve.model;

import lombok.Data;
import me.ghui.fruit.annotations.Pick;

@Data
public class OrderDetail {

    @Pick("body > div.w > div:nth-child(1) > div.list-item-box > div:nth-child(2) > div.item-detail")
    private String orderNo;

    @Pick("body > div.w > div:nth-child(1) > div.list-item-box > div:nth-child(4) > div.item-detail")
    private String orderDateTime;

    @Pick("body > div.w > div.detailinfo.activeinfo > div.activedetail > div.forcheck > ul > li:nth-child(1) > span.active-li-right")
    private String useDate;

    @Pick("body > div.w > div.detailinfo.activeinfo > div.activedetail > div.forcheck > ul > li:nth-child(2) > span.active-li-right.segmentStr")
    private String useTime;

    @Pick("body > div.w > div.detailinfo.activeinfo > div.activedetail > div.forcheck > ul > li:nth-child(4) > span.active-li-right.ticketTypeName")
    private String venueSiteInfo;
}
