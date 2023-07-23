package com.xpress.airtimevtu.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void registerCustomerTestAndPerformLoginTest() throws Exception {
        String content = "{\n" +
                "  \"email\": \"toska@gmail.com\",\n" +
                "  \"password\": \"toska12345\",\n" +
                "  \"firstName\": \"Emma\",\n" +
                "  \"lastName\": \"Ben\",\n" +
                "  \"phoneNumber\": \"08133310133\"\n" +
                "}";
        mockMvc.perform(post("/customers/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andDo(print());

        String credentials = "{\n" +
                "    \"email\": \"toska@gmail.com\",\n" +
                "    \"password\": \"toska12345\"\n" +
                "}";
        mockMvc.perform(post("/customers/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(credentials))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void purchaseAirtime() {
    }
}