package com.thinkVendor.demoone.implement;

import com.thinkVendor.demoone.CloudVendor;
import com.thinkVendor.demoone.repository.CloudVendorRepository;
import com.thinkVendor.demoone.service.CloudVendorServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudServiceImplementation implements CloudVendorServices {
    CloudVendorRepository cloudVendorRepository;

    public CloudServiceImplementation(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Successfully Created.";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Successfully Updated.";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Successfully Deleted";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        return cloudVendorRepository.findById(cloudVendorId).get();

    }

    @Override
    public List<CloudVendor> listOfCloudVendor() {
        return cloudVendorRepository.findAll();
    }
}
