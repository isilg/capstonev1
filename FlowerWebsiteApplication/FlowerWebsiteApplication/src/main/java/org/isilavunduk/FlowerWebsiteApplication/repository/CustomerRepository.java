package org.isilavunduk.FlowerWebsiteApplication.repository;
import org.isilavunduk.FlowerWebsiteApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {


}