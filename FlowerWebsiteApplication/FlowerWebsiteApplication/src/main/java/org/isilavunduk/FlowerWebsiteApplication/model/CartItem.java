package org.isilavunduk.FlowerWebsiteApplication.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


//Cart Items represent the items that a Customer has added to their cart before placing an Order
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Long itemId;


 //Multiple CartItem instances can have the same customer_id. One customer can have multiple cart items
 //Main entity will be fetched and related entity should be loaded lazily(not all the related entities will be called from DB at the same time. FetchType.LAZY)
 //tells Hibernate to only fetch the entities we want from DB when we use the relationship) It will be fetched from the DB only when it is actually accessed
 //When there is @ManyToOne relationship, and you expect that the related entity isn't always needed or accessed together with the main entity. This can
 //help optimize the performance of the application by reducing unnecessary DB queries
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)     //customer_id is FK of customer table
    private Customer customer;

//mappedBy specifies the field name in the CartItem entity that manages the relationship, which is "chocolates"
    @OneToMany(mappedBy = "cartItems", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flower> flowers;

    @OneToMany(mappedBy = "cartItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chocolate> chocolates;

}