package com.thinkVendor.demoone.implement;

import com.thinkVendor.demoone.CloudVendor;
import com.thinkVendor.demoone.repository.CloudVendorRepository;
import com.thinkVendor.demoone.service.CloudVendorServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CloudServiceImplementationTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorServices cloudVendorServices;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        cloudVendorServices=new CloudServiceImplementation(cloudVendorRepository);
        cloudVendor=new CloudVendor("1","GCP","NZ","1542369807");

    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void createCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorServices.createCloudVendor(cloudVendor)).isEqualTo("Successfully Created.");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorServices.updateCloudVendor(cloudVendor)).isEqualTo("Successfully Updated.");
    }

    @Test
    void deleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);
        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
        assertThat(cloudVendorServices.deleteCloudVendor("1")).isEqualTo("Successfully Deleted.");
    }

    @Test
    void getCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorServices.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testListOfCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorServices.listOfCloudVendor().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }

    @Test
    void testGetByVendorName() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findByVendorName("Amazon")).
                thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));

        assertThat(cloudVendorServices.getCloudVendorByName("Amazon").get(0).getVendorId()).
                isEqualTo(cloudVendor.getVendorId());
    }

    
}