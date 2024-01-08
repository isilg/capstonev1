package org.isilavunduk.FlowerWebsiteApplication.service;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerResponse;
import org.springframework.stereotype.Service;

//This Interface contains methods to retrieve, add, update, and delete flower data using a FlowerRepository.

@Service
public interface FlowerService {

    FlowerDto getFlowerDtoByName(String name);
    FlowerResponse getAllFlowers(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteFlower(String name);

}
