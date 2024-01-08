package org.isilavunduk.FlowerWebsiteApplication.service.impl;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerDto;
import org.isilavunduk.FlowerWebsiteApplication.model.Flower;
import org.isilavunduk.FlowerWebsiteApplication.exception.ResourceNotFoundException;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerResponse;
import org.isilavunduk.FlowerWebsiteApplication.repository.FlowerRepository;
import org.isilavunduk.FlowerWebsiteApplication.service.FlowerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;

    //CONSTRUCTOR
    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }



    @Override
    public FlowerDto getFlowerDtoByName(String name) {
        Flower flower = flowerRepository.findByName(name)
                .orElseThrow(() ->new ResourceNotFoundException("Flower not found with name: " + name));
        return mapToDTO(flower);        //mapToDto converts the flower entity to a FlowerDto

    }

    @Override
    public FlowerResponse getAllFlowers(int pageNo, int pageSize, String sortBy, String sortDir) {

        //If sortDir is in ascending order, then we'll create a sort obj with ascending, otherwise we'll create it in descending order
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

     //Create Pageable instance and pass that instance to findALL()
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

       Page<Flower> flowers = flowerRepository.findAll(pageable);

     //get content from page obj using getContent() method
        List<Flower> listOfFlower = flowers.getContent();

      List<FlowerDto> content =  listOfFlower.stream()
                                               .map(flower -> mapToDTO(flower))
                                               .collect(Collectors.toList());
        FlowerResponse flowerResponse = new FlowerResponse();
        flowerResponse.setContent(content);
        flowerResponse.setPageNo(flowers.getNumber());
        flowerResponse.setPageSize(flowers.getSize());
        flowerResponse.setTotalElements(flowers.getTotalElements());
        flowerResponse.setTotalPages(flowers.getTotalPages());
        flowerResponse.setLast(flowers.isLast());

        return flowerResponse;

    }

    

    @Override
    public void deleteFlower(String name) {
        //Check whether flower exist in DB or not
        Flower flower = flowerRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Flower", "name", name));
        flowerRepository.delete(flower);
    }

    // convert Entity into DTO  flower
    private FlowerDto mapToDTO(Flower flower){
        FlowerDto flowerDTO = new FlowerDto();
        flowerDTO.setId(flower.getId());
        flowerDTO.setName(flower.getName());
        flowerDTO.setPrice(flower.getPrice());
        return flowerDTO;
    }

    //Convert DTO to entity
    private Flower mapToEntity(FlowerDto flowerDTO){
        Flower flower = new Flower();
        flower.setName(flowerDTO.getName());
        flower.setPrice(flowerDTO.getPrice());
        return flower;
    }
}
