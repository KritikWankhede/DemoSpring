package com.thinkVendor.demoone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.thinkVendor.demoone.CloudVendor;
import com.thinkVendor.demoone.service.CloudVendorServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CloudVendorAPIService.class)
class CloudVendorAPIServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorServices cloudVendorServices;
    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    List<CloudVendor> cloudVendorList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        cloudVendorOne=new CloudVendor("1","GCP","NZ","1542369807");
        cloudVendorTwo=new CloudVendor("2","Amazon","USA","7089632451");
        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorServices.getCloudVendor("1")).thenReturn(cloudVendorOne);
        this.mockMvc.perform(get("/cloudVendor/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorServices.listOfCloudVendor()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudVendor")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateCloudVendor() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter=objectMapper.writer().withDefaultPrettyPrinter();
        String requestJSON= objectWriter.writeValueAsString(cloudVendorOne);

        when(cloudVendorServices.createCloudVendor(cloudVendorOne)).thenReturn("Successfully Created.");
        this.mockMvc.perform(post("/cloudVendor").contentType(MediaType.APPLICATION_JSON).content(requestJSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendor() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter=objectMapper.writer().withDefaultPrettyPrinter();
        String requestJSON= objectWriter.writeValueAsString(cloudVendorOne);

        when(cloudVendorServices.updateCloudVendor(cloudVendorOne)).thenReturn("Successfully Updated.");
        this.mockMvc.perform(put("/cloudVendor").contentType(MediaType.APPLICATION_JSON).content(requestJSON)).andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testDeleteCloudVendor() throws Exception {
        when(cloudVendorServices.deleteCloudVendor("1")).thenReturn("Successfully Deleted.");
        this.mockMvc.perform(delete("/cloudVendor/1")).andDo(print()).andExpect(status().isOk());
    }
}