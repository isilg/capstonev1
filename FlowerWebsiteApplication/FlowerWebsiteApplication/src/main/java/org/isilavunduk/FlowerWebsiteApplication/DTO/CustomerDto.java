package org.isilavunduk.FlowerWebsiteApplication.DTO;
import lombok.Data;


@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
