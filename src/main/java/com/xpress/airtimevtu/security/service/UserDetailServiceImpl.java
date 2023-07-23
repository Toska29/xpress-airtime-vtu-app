package com.xpress.airtimevtu.security.service;

import com.xpress.airtimevtu.app.model.Customer;
import com.xpress.airtimevtu.app.repository.CustomerRepository;
import com.xpress.airtimevtu.security.model.CustomJwtUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("customer does not exist"));

        return new CustomJwtUser(customer);
    }
}
