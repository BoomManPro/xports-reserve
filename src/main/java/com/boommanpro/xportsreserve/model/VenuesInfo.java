package com.boommanpro.xportsreserve.model;

import lombok.Data;
import me.ghui.fruit.annotations.Pick;

import java.util.Objects;

@Data
public class VenuesInfo {
    /**
     * 唯一id
     */
    @Pick(attr = "field-segment-id")
    private String id;
    /**
     * 价格
     */
    @Pick(attr = "price")
    private Integer price;
    /**
     * 开始时间
     */
    @Pick(attr = "start-time")
    private Integer startHour;
    /**
     * 结束时间
     */
    @Pick(attr = "end-time")
    private Integer endHour;
    /**
     * 场地状态
     */
    @Pick(attr = "state")
    private Integer state;
    /**
     * 场地名称
     */
    @Pick(attr = "venue-name")
    private String venueName;

    private String venueSite;

    public boolean timeEquals(ReserveRequire require) {
        return startHour.equals(require.getStartHour()) && endHour.equals(require.getEndHour());
    }

    public boolean canReserve() {
        return Objects.equals(0, state);
    }

    public String getTimeKey() {
        return String.format("%s-%s", startHour, endHour);
    }
}
