package org.isilavunduk.FlowerWebsiteApplication.repository;
import org.isilavunduk.FlowerWebsiteApplication.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCustomerId(Long customerId);

    Optional<CartItem> findByCustomerIdAndItemId(Long customerId, Long itemId);


}
