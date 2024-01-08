package org.isilavunduk.FlowerWebsiteApplication.service;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CustomerDto;

public interface CustomerService {

    // Update customer information
    CustomerDto updateCustomer(CustomerDto customerDto);

    // Get customer information by ID
    CustomerDto getCustomerById(long customerId);

    void deleteCustomer(long customerId);

    CustomerDto registerNewCustomer(CustomerDto registrationDto);

}
