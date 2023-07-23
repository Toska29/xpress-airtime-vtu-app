package com.xpress.airtimevtu.app.service;

import com.xpress.airtimevtu.app.dto.customer.CustomerRequestDto;
import com.xpress.airtimevtu.app.dto.customer.CustomerResponseDto;
import com.xpress.airtimevtu.app.model.Customer;
import com.xpress.airtimevtu.app.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RestTemplate restTemplate;

    ModelMapper mapper;
    CustomerService customerService;

    @BeforeEach
    public void startWith(){
        mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        customerService = new CustomerServiceImpl(customerRepository, passwordEncoder, restTemplate, mapper);
    }


    @Test
    void registerCustomer() {
        //given
        CustomerRequestDto requestDto = new CustomerRequestDto();
        requestDto.setEmail("emma@gmail.com");
        requestDto.setPassword("toska12345");
        requestDto.setLastName("Ben");
        requestDto.setFirstName("Emma");
        requestDto.setPhoneNumber("08133333333");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmail(requestDto.getEmail());
        customer.setLastName(requestDto.getLastName());
        customer.setFirstName(requestDto.getFirstName());
        customer.setPhoneNumber(requestDto.getPhoneNumber());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        //when
        CustomerResponseDto registerCustomer = customerService.registerCustomer(requestDto);

        //then
        verify(customerRepository).save(any(Customer.class));
        assertThat(registerCustomer).isNotNull();
        assertThat(registerCustomer.getEmail()).isEqualTo("emma@gmail.com");
    }

    @Test
    void purchaseAirtime() {

    }
}