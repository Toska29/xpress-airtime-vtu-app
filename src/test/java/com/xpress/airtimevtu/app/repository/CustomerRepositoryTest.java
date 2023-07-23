package com.xpress.airtimevtu.app.repository;

import com.xpress.airtimevtu.app.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Profile("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Test that Customer can be found by email")
    void findByEmailTest() {
        //given
        Customer customer = new Customer();
        customer.setEmail("emma@gmail.com");
        customer.setFirstName("Emma");
        customer.setLastName("Ben");

        Customer savedCustomer = customerRepository.save(customer);

        //when
        Optional<Customer> retrievedCustomer = customerRepository.findByEmail(customer.getEmail());

        //then
        assertThat(retrievedCustomer).isNotEmpty();
        assertThat(retrievedCustomer.get()).isInstanceOf(Customer.class);
        assertThat(retrievedCustomer.get().getFirstName()).isEqualTo("Emma");
    }

    @Test
    @DisplayName("Test that customer can be check by email")
    void existsByEmailTest() {
        //given
        Customer customer = new Customer();
        customer.setEmail("emma@gmail.com");
        customer.setFirstName("Emma");
        customer.setLastName("Ben");

        Customer savedCustomer = customerRepository.save(customer);

        //when
        boolean response = customerRepository.existsByEmail(customer.getEmail());

        //then
        assertThat(response).isTrue();
    }

}