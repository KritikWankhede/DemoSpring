package com.thinkVendor.demoone.repository;

import com.thinkVendor.demoone.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest {

        @Autowired
        private CloudVendorRepository cloudVendorRepository;
        CloudVendor cloudVendor;

        @BeforeEach
        void setUp(){
            cloudVendor=new CloudVendor("1","Google","USA","1254680");
            cloudVendorRepository.save(cloudVendor);
        }
         @AfterEach
         void tearDowm(){
            cloudVendor=null;
            cloudVendorRepository.deleteAll();
        }

        @Test
        void testByVendorName_Found(){
            List<CloudVendor> cloudVendorList= cloudVendorRepository.findByVendorName("Google");
            assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
            assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
            //assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
        }

        @Test
        void testByVendorName_NotFound(){
            List<CloudVendor> cloudVendorList= cloudVendorRepository.findByVendorName("Azure");
            assertThat(cloudVendorList.isEmpty()).isTrue();
            //assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
        }

}
