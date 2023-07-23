package com.xpress.airtimevtu.app.service;

import com.xpress.airtimevtu.app.dto.airtime.AirtimeRequestDto;
import com.xpress.airtimevtu.app.dto.airtime.AirtimeResponseDto;
import com.xpress.airtimevtu.app.dto.customer.CustomerRequestDto;
import com.xpress.airtimevtu.app.dto.customer.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto);

    AirtimeResponseDto purchaseAirtime(AirtimeRequestDto airtimeRequestDto);

}
