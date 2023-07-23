package com.xpress.airtimevtu.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role = Role.CUSTOMER;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean isActive = true;
    @CreationTimestamp
    private LocalDateTime dateTimeRegistered;

}
