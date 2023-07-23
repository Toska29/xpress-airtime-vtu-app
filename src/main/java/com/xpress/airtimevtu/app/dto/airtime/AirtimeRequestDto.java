package com.xpress.airtimevtu.app.dto.airtime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AirtimeRequestDto {
    private String biller;
    private String phoneNumber;
    private Integer amount;
}
