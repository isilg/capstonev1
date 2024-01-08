package org.isilavunduk.FlowerWebsiteApplication.service;

import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateResponse;
import org.springframework.stereotype.Service;

@Service
public interface ChocolateService {
    ChocolateResponse getAllChocolates(int pageNo, int pageSize, String sortBy, String sortDir);
    ChocolateDto getChocolateDtoByName(String name);
}