package com.xpress.airtimevtu.app.service;

import com.xpress.airtimevtu.app.dto.airtime.AirtimeRequestDto;
import com.xpress.airtimevtu.app.dto.airtime.AirtimeResponseDto;
import com.xpress.airtimevtu.app.dto.airtime.Detail;
import com.xpress.airtimevtu.app.dto.airtime.XpressRequestEntity;
import com.xpress.airtimevtu.app.dto.customer.CustomerRequestDto;
import com.xpress.airtimevtu.app.dto.customer.CustomerResponseDto;
import com.xpress.airtimevtu.app.model.Customer;
import com.xpress.airtimevtu.app.model.UniqueCode;
import com.xpress.airtimevtu.app.repository.CustomerRepository;
import com.xpress.airtimevtu.exception.CustomerRegistrationException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Value("${PUBLIC_KEY}")
    private String publicKey;
    @Value("${PRIVATE_KEY}")
    private String privateKey;

    @Override
    public CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto) {
        if(customerRepository.existsByEmail(customerRequestDto.getEmail())){
            throw new CustomerRegistrationException("customer already exist");
        }

        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        customer.setPassword(passwordEncoder.encode(customerRequestDto.getPassword()));

        Customer savedCustomer = customerRepository.save(customer);

        return buildCustomerResponseDto(savedCustomer);
    }

    @Override
    public AirtimeResponseDto purchaseAirtime(AirtimeRequestDto airtimeRequestDto) {
        String requestId = UUID.randomUUID().toString();
        UniqueCode uniqueCode = UniqueCode.getUniqueCodeByBiller(airtimeRequestDto.getBiller());

        XpressRequestEntity requestBody = new XpressRequestEntity();
        requestBody.setDetail(new Detail(airtimeRequestDto.getPhoneNumber(), airtimeRequestDto.getAmount()));
        requestBody.setRequestId(requestId);
        requestBody.setUniqueCode(uniqueCode.name());

        String url = "https://billerstest.xpresspayments.com:9603/api/v1/airtime/fulfil";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + publicKey);
        headers.set("PaymentHash", privateKey);
        headers.set("Channel", "https://billerstest.xpresspayments.com:9603/api/v1/airtime/fulfil");
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<AirtimeResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, AirtimeResponseDto.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }else {
            return new AirtimeResponseDto(requestId, null, "FAILED", "FAILED", null);
        }

    }

    private CustomerResponseDto buildCustomerResponseDto(Customer customer){
        return modelMapper.map(customer, CustomerResponseDto.class);
    }
}
