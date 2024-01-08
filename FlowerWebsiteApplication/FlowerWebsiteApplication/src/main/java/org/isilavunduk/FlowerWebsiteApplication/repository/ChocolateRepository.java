package org.isilavunduk.FlowerWebsiteApplication.repository;
import org.isilavunduk.FlowerWebsiteApplication.model.Chocolate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
//By extending JpaRepository(it's an interface provided by Spring Data JPA) we can use CRUD OPERATIONS for entity and talk to DB
public interface ChocolateRepository extends JpaRepository<Chocolate, Long> {    //entity name, data type of PK
    Optional<Chocolate> findByName(String name);
}
