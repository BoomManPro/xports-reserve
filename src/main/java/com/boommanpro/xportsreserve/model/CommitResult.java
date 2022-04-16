package com.boommanpro.xportsreserve.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class CommitResult {
    @JsonProperty("sysdate")
    private String sysdate;
    @JsonProperty("trade")
    private TradeDTO trade;
    @JsonProperty("payFee")
    private Integer payFee;
    @JsonProperty("tradeExpireTime")
    private String tradeExpireTime;
    @JsonProperty("error")
    private Integer error;
    @JsonProperty("message")
    private String message;
    @JsonProperty("ticketDetails")
    private List<TicketDetailsDTO> ticketDetails;
    @JsonProperty("tradeId")
    private Long tradeId;
    @JsonProperty("resultCode")
    private Integer resultCode;

    public boolean reserveError() {
        return !Objects.isNull(resultCode);
    }

    public boolean reserveSuccess() {
        return "ok".equals(message);
    }

    @NoArgsConstructor
    @Data
    public static class TradeDTO {
        @JsonProperty("tradeId")
        private Long tradeId;
        @JsonProperty("subscribeId")
        private Long subscribeId;
        @JsonProperty("tradeTypeCode")
        private Integer tradeTypeCode;
        @JsonProperty("priority")
        private Integer priority;
        @JsonProperty("subscribeState")
        private String subscribeState;
        @JsonProperty("acceptDate")
        private String acceptDate;
        @JsonProperty("acceptMonth")
        private String acceptMonth;
        @JsonProperty("tradeStaffId")
        private Integer tradeStaffId;
        @JsonProperty("cancelTag")
        private String cancelTag;
        @JsonProperty("payState")
        private String payState;
        @JsonProperty("payTfee")
        private Integer payTfee;
        @JsonProperty("centerId")
        private Integer centerId;
        @JsonProperty("venueId")
        private Integer venueId;
        @JsonProperty("expireTime")
        private String expireTime;
        @JsonProperty("serviceId")
        private Integer serviceId;
        @JsonProperty("tradeDesc")
        private String tradeDesc;
        @JsonProperty("channelId")
        private Integer channelId;
        @JsonProperty("channelAppId")
        private Integer channelAppId;
    }

    @NoArgsConstructor
    @Data
    public static class TicketDetailsDTO {
        @JsonProperty("ticketId")
        private Long ticketId;
        @JsonProperty("tradeId")
        private Long tradeId;
        @JsonProperty("ticketNo")
        private String ticketNo;
        @JsonProperty("serviceId")
        private Integer serviceId;
        @JsonProperty("venueId")
        private Integer venueId;
        @JsonProperty("fieldId")
        private Integer fieldId;
        @JsonProperty("fieldName")
        private String fieldName;
        @JsonProperty("startSegment")
        private Integer startSegment;
        @JsonProperty("endSegment")
        private Integer endSegment;
        @JsonProperty("startTime")
        private String startTime;
        @JsonProperty("endTime")
        private String endTime;
        @JsonProperty("payMoney")
        private Integer payMoney;
        @JsonProperty("discount")
        private Integer discount;
        @JsonProperty("state")
        private String state;
        @JsonProperty("createTime")
        private String createTime;
        @JsonProperty("ticketSourceType")
        private String ticketSourceType;
        @JsonProperty("ticketDrawer")
        private Integer ticketDrawer;
        @JsonProperty("effectDate")
        private String effectDate;
        @JsonProperty("expireDate")
        private String expireDate;
        @JsonProperty("ecardNo")
        private String ecardNo;
        @JsonProperty("custName")
        private String custName;
        @JsonProperty("ticketType")
        private Integer ticketType;
        @JsonProperty("priceItem")
        private Long priceItem;
        @JsonProperty("couponAmount")
        private Integer couponAmount;
        @JsonProperty("groupTag")
        private String groupTag;
        @JsonProperty("fullTag")
        private String fullTag;
        @JsonProperty("regularPrice")
        private Integer regularPrice;
        @JsonProperty("playerNum")
        private Integer playerNum;
        @JsonProperty("ticketTypeName")
        private String ticketTypeName;
    }
}
