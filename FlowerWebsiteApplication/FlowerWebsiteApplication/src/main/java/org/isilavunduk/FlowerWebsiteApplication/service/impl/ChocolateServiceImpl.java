package org.isilavunduk.FlowerWebsiteApplication.service.impl;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateResponse;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerDto;
import org.isilavunduk.FlowerWebsiteApplication.model.Chocolate;
import org.isilavunduk.FlowerWebsiteApplication.exception.ResourceNotFoundException;
import org.isilavunduk.FlowerWebsiteApplication.model.Flower;
import org.isilavunduk.FlowerWebsiteApplication.repository.ChocolateRepository;
import org.isilavunduk.FlowerWebsiteApplication.service.ChocolateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChocolateServiceImpl implements ChocolateService {

    private final ChocolateRepository chocolateRepository;

    public ChocolateServiceImpl(ChocolateRepository chocolateRepository) {
        this.chocolateRepository = chocolateRepository;
    }


    @Override
    public ChocolateResponse getAllChocolates(int pageNo, int pageSize, String sortBy, String sortDir) {

        //If sortDir is in ascending order, then we'll create a sort obj with ascending, otherwise we'll create it in descending order
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //Create Pageable instance and pass that instance to findALL()
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Chocolate> chocolates = chocolateRepository.findAll(pageable);

        //get content from page obj using getContent() method
        List<Chocolate> listOfChocolate = chocolates.getContent();

        List<ChocolateDto> content =  listOfChocolate.stream()
                                                     .map(chocolate -> mapToDto(chocolate))
                                                     .collect(Collectors.toList());
        ChocolateResponse chocolateResponse = new ChocolateResponse();
        chocolateResponse.setContent(content);
        chocolateResponse.setPageNo(chocolates.getNumber());
        chocolateResponse.setPageSize(chocolates.getSize());
        chocolateResponse.setTotalElements(chocolates.getTotalElements());
        chocolateResponse.setTotalPages(chocolates.getTotalPages());
        chocolateResponse.setLast(chocolates.isLast());

        return chocolateResponse;

    }



    @Override
    public ChocolateDto getChocolateDtoByName(String name) {    //findByName() retrieve the Chocolate entity
        Chocolate chocolate = chocolateRepository.findByName(name)
                .orElseThrow(() ->new ResourceNotFoundException("Chocolate not found with name: " + name));
        return mapToDto(chocolate);        //mapToDTO converts the Chocolate entity to a ChocolateDto.
    }



    // convert Entity into DTO
    private ChocolateDto mapToDto(Chocolate chocolate){
        ChocolateDto chocolateDto = new ChocolateDto();
        chocolateDto.setId(chocolate.getId());
        chocolateDto.setName(chocolate.getName());
        chocolateDto.setPrice(chocolate.getPrice());
        return chocolateDto;
    }


    //Convert DTO to entity
    private Chocolate mapToEntity(ChocolateDto chocolateDto){
        Chocolate chocolate = new Chocolate();
        chocolate.setName(chocolateDto.getName());
        chocolate.setPrice(chocolateDto.getPrice());
        return chocolate;
    }

}