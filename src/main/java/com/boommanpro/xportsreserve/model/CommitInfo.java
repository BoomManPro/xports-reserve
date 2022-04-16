package com.boommanpro.xportsreserve.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommitInfo {

    @JsonProperty("venueId")
    private String venueId = "1101000301";
    @JsonProperty("serviceId")
    private String serviceId = "1002";
    @JsonProperty("fieldType")
    private String fieldType;
    @JsonProperty("day")
    private String day;
    @JsonProperty("fieldInfo")
    private String fieldInfo;

    public CommitInfo(String fieldType, String day, String fieldInfo) {
        this.fieldType = fieldType;
        this.day = day;
        this.fieldInfo = fieldInfo;
    }
}
