package com.xpress.airtimevtu.app.dto.airtime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeResponseDto {
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("referenceId")
    private String referenceId;
    @JsonProperty("responseCode")
    private String responseCode;
    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonProperty("data")
    private Object data;

}
