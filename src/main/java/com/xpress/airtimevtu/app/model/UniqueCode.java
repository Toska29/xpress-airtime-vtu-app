package com.xpress.airtimevtu.app.model;

import com.xpress.airtimevtu.exception.AirtimeException;

import java.util.Arrays;

public enum UniqueCode {
    MTN_24207("MTN"), GLO_30387("GLO"), AIRTEL_22689("AIRTEL"), MOBILE_69358("9MOBILE");

    private final String biller;

    UniqueCode(String biller) {

        this.biller = biller;
    }

    public String getBiller() {
        return biller;
    }

    public static UniqueCode getUniqueCodeByBiller(String biller){
        return Arrays.stream(UniqueCode.values()).filter(uniqueCode -> uniqueCode.getBiller().equals(biller)).findFirst()
                .orElseThrow(() -> new AirtimeException("biller does not exist"));
    }
}
