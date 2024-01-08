package org.isilavunduk.FlowerWebsiteApplication.repository;
import org.isilavunduk.FlowerWebsiteApplication.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


//JpaRepository interface provides default implementations for common DB operations
@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {  //entity name, data type of PK

    Optional<Flower> findByName(String name);

}
