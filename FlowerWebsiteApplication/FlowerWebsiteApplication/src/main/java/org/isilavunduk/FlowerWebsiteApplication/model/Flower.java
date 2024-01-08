package org.isilavunduk.FlowerWebsiteApplication.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data     //generates getters and setters for all fields
@AllArgsConstructor
@NoArgsConstructor
@Entity   //Specifies that the class is an entity in the DB
@Table(name= "flowers")    //name of the corresponding DB table
public class Flower {

    @Id    //Specifies the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // PK values should be generated automatically by the DB
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItems;

}