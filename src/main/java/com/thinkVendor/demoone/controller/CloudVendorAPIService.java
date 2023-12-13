package com.thinkVendor.demoone.controller;

import com.thinkVendor.demoone.CloudVendor;
import com.thinkVendor.demoone.response.ResponseHandler;
import com.thinkVendor.demoone.service.CloudVendorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudVendor")
public class CloudVendorAPIService {
    CloudVendor cv;
    CloudVendorServices cloudVendorServices;

    public CloudVendorAPIService(CloudVendorServices cloudVendorServices) {
        this.cloudVendorServices = cloudVendorServices;
    }

    @GetMapping("{vendorId}")
        public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
            return ResponseHandler.responseBuilder("Requested Vendor Details Are Present.", HttpStatus.OK,cloudVendorServices.getCloudVendor(vendorId));
        }
    @GetMapping()
        public List<CloudVendor> getAllCloudVendorDetails(){
            return cloudVendorServices.listOfCloudVendor();
        }
        @PostMapping
        public String createCloudVendor(@RequestBody CloudVendor cv){
            cloudVendorServices.createCloudVendor(cv);
            return "Cloud Vendor Details Created.";
        }

        @PutMapping
        public String updateCloudVendor(@RequestBody CloudVendor cv){
            cloudVendorServices.updateCloudVendor(cv);
            return "Cloud Vendor Details Updated.";
        }

        @DeleteMapping("{vendorId}")
        public String deleteCloudVendor(@PathVariable("vendorId") String vendorId){
            cloudVendorServices.deleteCloudVendor(vendorId);
            return "Cloud Vendor Details Deleted Successfully.";
        }


}
