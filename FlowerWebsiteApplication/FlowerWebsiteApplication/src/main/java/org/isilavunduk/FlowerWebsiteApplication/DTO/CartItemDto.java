package org.isilavunduk.FlowerWebsiteApplication.DTO;
import lombok.Data;


//Using Dto classes to send data between Controller and Client. So we do not to directly exposing the entities to the Client as a response of REST API
//Dto classes are for data binding between controllers and services
@Data
public class CartItemDto {

    private Long id;
    private int quantity;
    private String name;
    private String email;
    private String address;

    public String getChocolateName() {
        return null;
    }

    public String getFlowerName() {
        return null;
    }
}
