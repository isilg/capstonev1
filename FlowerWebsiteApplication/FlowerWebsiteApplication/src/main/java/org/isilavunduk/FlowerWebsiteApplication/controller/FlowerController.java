package org.isilavunduk.FlowerWebsiteApplication.controller;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CartItemDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerResponse;
import org.isilavunduk.FlowerWebsiteApplication.service.CartItemService;
import org.isilavunduk.FlowerWebsiteApplication.service.FlowerService;
import org.isilavunduk.FlowerWebsiteApplication.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//This class manages operations related to flowers, such as viewing flower details, browsing flowers by category,
//and searching for specific flowers. It handles Web requests(=REST API Requests=>GET, POST, PUT, and DELETE) for flowers

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    private final FlowerService flowerService;
    private final CartItemService cartItemService ;

    public FlowerController(FlowerService flowerService, CartItemService cartItemService) {
        this.flowerService = flowerService;
        this.cartItemService = cartItemService;
    }


    //Add Flower to Shopping Cart then retrieve the details of that flower
    @PostMapping("/cart/{customerId}")
    public ResponseEntity<FlowerDto> addFlowerToCart(@PathVariable long customerId, @RequestBody FlowerDto flowerDto) {             //@RequestBody -Retrieve Request Body and convert it to Java Obj
        CartItemDto cartItem = cartItemService.addFlowerToCart(customerId, flowerDto);
        FlowerDto addedFlowerDto = flowerService.getFlowerDtoByName(cartItem.getFlowerName());   //Returns details of the added chocolate
        return new ResponseEntity<>(addedFlowerDto, HttpStatus.CREATED);
    }



    //get all flowers REST API
    @GetMapping
    public FlowerResponse getAllFlowers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue =AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,   //sort post by id
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return flowerService.getAllFlowers(pageNo, pageSize, sortBy, sortDir);
    }


    //Delete Flower By Name REST API
    @DeleteMapping("/cart/{customerId}/{flowerId}")
    public ResponseEntity<String> deleteFlower(@PathVariable String name){
            flowerService.deleteFlower(name);
            return new ResponseEntity<>("Flower Deleted Successfully", HttpStatus.NO_CONTENT);
        }
}