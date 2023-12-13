package com.thinkVendor.demoone.implement;

import com.thinkVendor.demoone.CloudVendor;
import com.thinkVendor.demoone.exception.CloudVendorNotFoundException;
import com.thinkVendor.demoone.repository.CloudVendorRepository;
import com.thinkVendor.demoone.response.ResponseHandler;
import com.thinkVendor.demoone.service.CloudVendorServices;
import org.springframework.http.HttpStatus;
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
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty()){
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not Exist.");
        }

        return cloudVendorRepository.findById(cloudVendorId).get();

    }

    @Override
    public List<CloudVendor> listOfCloudVendor() {
        return cloudVendorRepository.findAll();
    }
}
