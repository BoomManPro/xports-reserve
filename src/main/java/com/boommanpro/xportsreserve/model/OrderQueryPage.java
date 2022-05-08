package com.boommanpro.xportsreserve.model;

import lombok.Data;
import me.ghui.fruit.annotations.Pick;

import java.util.List;

@Data
public class OrderQueryPage {

    @Pick("body > div.order-list > div > div.detail-info")
    private List<OrderQueryInfo> orderList;

    @Data
    public static class OrderQueryInfo {
        @Pick("div.time")
        private String reserveDateTime;

        @Pick(attr = "data-orderstate")
        private Integer orderState;
    }
}
