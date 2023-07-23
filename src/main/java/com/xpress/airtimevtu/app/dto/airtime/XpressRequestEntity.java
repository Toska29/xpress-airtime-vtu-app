package com.xpress.airtimevtu.app.dto.airtime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class XpressRequestEntity {
    private String requestId;
    private String uniqueCode;
    private Detail detail;
}
