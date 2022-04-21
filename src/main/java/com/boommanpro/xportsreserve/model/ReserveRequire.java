package com.boommanpro.xportsreserve.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReserveRequire implements Comparable<ReserveRequire> {

    /**
     * 开始时间
     */
    private Integer startHour;
    /**
     * 结束时间
     */
    private Integer endHour;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 是否预定成功
     */
    private boolean reserved;

    private String targetDate;

    public ReserveRequire(Integer startHour, Integer endHour, Integer order, String targetDate) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.order = order;
        this.targetDate = targetDate;
    }

    @Override
    public int compareTo(ReserveRequire o) {
        return order.compareTo(o.order);
    }
}
