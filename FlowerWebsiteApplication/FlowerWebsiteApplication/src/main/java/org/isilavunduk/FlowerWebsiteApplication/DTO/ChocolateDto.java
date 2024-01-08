package org.isilavunduk.FlowerWebsiteApplication.DTO;
import lombok.Data;

@Data   //INTERNALLY GENERATES GETTER, SETTER, CONSTRUCTOR W ARGS
public class ChocolateDto {
        private long id;
        private String name;
        private double price;
        private String content;
}
