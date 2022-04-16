package com.boommanpro.xportsreserve.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FtServerResult {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("pushid")
        private String pushid;
        @JsonProperty("readkey")
        private String readkey;
        @JsonProperty("error")
        private String error;
        @JsonProperty("errno")
        private Integer errno;
    }
}
