package com.xpress.airtimevtu.app.controller;

import com.xpress.airtimevtu.app.dto.airtime.AirtimeRequestDto;
import com.xpress.airtimevtu.app.dto.airtime.AirtimeResponseDto;
import com.xpress.airtimevtu.app.dto.customer.CustomerRequestDto;
import com.xpress.airtimevtu.app.dto.customer.CustomerResponseDto;
import com.xpress.airtimevtu.app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto = customerService.registerCustomer(customerRequestDto);

        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/airtime/purchase")
    public ResponseEntity<?> purchaseAirtime(@RequestBody AirtimeRequestDto airtimeRequestDto){
        AirtimeResponseDto airtimeResponseDto = customerService.purchaseAirtime(airtimeRequestDto);
        return ResponseEntity.ok(airtimeResponseDto);
    }
}
