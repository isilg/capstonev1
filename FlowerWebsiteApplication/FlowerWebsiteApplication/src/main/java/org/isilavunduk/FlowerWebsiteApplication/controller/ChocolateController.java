package org.isilavunduk.FlowerWebsiteApplication.controller;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CartItemDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateResponse;
import org.isilavunduk.FlowerWebsiteApplication.service.CartItemService;
import org.isilavunduk.FlowerWebsiteApplication.service.ChocolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//This class allows users view chocolates, add or remove them in their shopping cart

@RestController
@RequestMapping("/api/chocolates")
public class ChocolateController {

    private final ChocolateService chocolateService;
    private final CartItemService cartItemService;

    @Autowired
    public ChocolateController(ChocolateService chocolateService, CartItemService cartItemService) {
        this.chocolateService = chocolateService;
        this.cartItemService = cartItemService;
    }


    //View all chocolates on the website
    @GetMapping
    public ChocolateResponse getAllChocolates(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return chocolateService.getAllChocolates(pageNo, pageSize, sortBy, sortDir);
    }



    //Add Chocolate to Shopping Cart then retrieving the details of that chocolate
    @PostMapping("/cart/{customerId}")
    public ResponseEntity<ChocolateDto> addChocolateToCart(@PathVariable long customerId, @RequestBody ChocolateDto chocolateDto) {             //@RequestBody -Retrieve Request Body and convert it to Java Obj
        CartItemDto cartItem = cartItemService.addChocolateToCart(customerId, chocolateDto);
        ChocolateDto addedChocolateDto = chocolateService.getChocolateDtoByName(cartItem.getChocolateName());   //Returns details of the added chocolate
        return new ResponseEntity<>(addedChocolateDto, HttpStatus.CREATED);
    }



    // Delete chocolate from shopping cart
    @DeleteMapping("/cart/{customerId}/{chocolateId}")
    public ResponseEntity<String> deleteChocolateFromCart(@PathVariable long customerId, @PathVariable long chocolateId) {
        boolean isRemoved = cartItemService.removeChocolateFromCart(customerId, chocolateId);
        if (isRemoved) {
            return ResponseEntity.ok("Chocolate removed from cart successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove chocolate from cart");
        }
    }
}
