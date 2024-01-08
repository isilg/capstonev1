package org.isilavunduk.FlowerWebsiteApplication.controller;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CustomerDto;
import org.isilavunduk.FlowerWebsiteApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Handles user-related operations, including user registration and login.
//It has methods for viewing order history and managing customer information.

    @RestController
    @RequestMapping("/api/customers")
    public class CustomerController{

        private final CustomerService customerService;

        //Constructor
        @Autowired
        public CustomerController(CustomerService customerService) {
            this.customerService = customerService;
        }



        //This method will handle the registration of a new customer. The customer's details will be saved in DB,
        //and it will return the unique customer ID along with a success message
        @PostMapping("/register")
        public ResponseEntity<String> registerNewCustomer(@RequestBody CustomerDto registrationDto) {
            CustomerDto createdCustomer = customerService.registerNewCustomer(registrationDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Customer successfully registered with ID: " + createdCustomer.getId());
        }



        //Get Customer Using CustomerId
        @GetMapping("/{customerId}")
        public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long customerId) {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
            if (customerDto == null) {
                return ResponseEntity.notFound().build();
            }else {
                return ResponseEntity.ok(customerDto);
            }
        }



        //This allows customers to update their name or email
        @PutMapping("/{customerId}")
        public ResponseEntity<CustomerDto> updateCustomer(
                @PathVariable("customerId") long customerId,
                @RequestParam(required = false) String firstName,
                @RequestParam(required = false) String lastName,
                @RequestParam(required = false) String email,
                @RequestParam(required = false) String address ) {

            // Validate that customerId exists and handle errors if not found
            CustomerDto customerDto = customerService.getCustomerById(customerId);

            if (customerDto == null) {
                // Handle the case where the customer with the given ID doesn't exist
                return ResponseEntity.notFound().build();
            }

            // Update the customer's name, email, address if provided
            if (firstName != null) {
                customerDto.setFirstName(firstName);
            }

            if (lastName != null) {
                customerDto.setLastName(lastName);
            }

            if (email != null) {
                customerDto.setEmail(email);
            }

            if(address != null) {
                customerDto.setAddress(address);
            }

            // Save the updated customer
            customerDto = customerService.updateCustomer(customerDto);

            return ResponseEntity.ok(customerDto);
        }


        @DeleteMapping("/{customerId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteCustomer(@PathVariable long customerId) {
            customerService.deleteCustomer(customerId);
        }

    }
