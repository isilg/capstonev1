package org.isilavunduk.FlowerWebsiteApplication.service.impl;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CustomerDto;
import org.isilavunduk.FlowerWebsiteApplication.exception.ResourceNotFoundException;
import org.isilavunduk.FlowerWebsiteApplication.model.Customer;
import org.isilavunduk.FlowerWebsiteApplication.repository.CustomerRepository;
import org.isilavunduk.FlowerWebsiteApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

 //Inject CustomerRepository in CustomerServiceImpl Class
    private final CustomerRepository customerRepository;

 //Constructor
    @Autowired    // constructor is used for dependency injection
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    //Handles the registration of a new customer
    public CustomerDto registerNewCustomer(CustomerDto registrationDto) {
        // Convert DTO to entity
        Customer customer = mapToEntity(registrationDto);

        // Save the new customer
        Customer newCustomer = customerRepository.save(customer);

        // Update the DTO with the generated ID
        registrationDto.setId(newCustomer.getId());
        return registrationDto;
    }




    @Override
    //Updates an existing customer's information.
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        //Fetch the customer from DB
        Customer existingCustomer = customerRepository.findById(customerDto.getId())
              .orElseThrow(() ->new ResourceNotFoundException("Customer not found with ID: "+customerDto.getId()));

        //Update the customer's information
        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setAddress(customerDto.getAddress());

        // Save the updated customer
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        // Convert and return the updated customer
        return mapToDto(updatedCustomer);
    }


    @Override
    //Retrieves a customer's information based on their ID.
    public CustomerDto getCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
        return mapToDto(customer);
    }


    @Override
    //Deletes a customer from the database.
    public void deleteCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
        customerRepository.delete(customer);
    }



    // convert Entity into DTO
    private CustomerDto mapToDto(Customer customer){
        CustomerDto customerDTO = new CustomerDto();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    //Convert DTO to entity
    private Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        return customer;
    }
}
