package org.isilavunduk.FlowerWebsiteApplication.controller;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CartItemDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CustomerDto;
import org.isilavunduk.FlowerWebsiteApplication.model.CartItem;
import org.isilavunduk.FlowerWebsiteApplication.service.CartItemService;
import org.isilavunduk.FlowerWebsiteApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//This class is responsible for handling shopping cart functionality. It allows users
//to view their cart, add or remove items from the cart

@RestController
@RequestMapping("/api/cart/customers/{customerId}")
public class CartItemController {

    private final CartItemService cartItemService;


    //Constructor
    //CartItemService will be injected into this constructor or anything we pass into this constructor should be injected
    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }



    //@PostMapping allows customers to add items to their shopping cart.
    @PostMapping
    public ResponseEntity<CartItemDto> createCartItem(
                        @PathVariable("customerId") long customerId,     //This annotation specifies the name of the path variable as "customerId"
                        @RequestBody CartItemDto cartItemDto) {
        CartItemDto createdCartItem = cartItemService.addItemToCart(customerId, cartItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartItem);
    }



   //Customers view the contents of their shopping cart. Retrieves the customer's cart items and return them as a response
    @GetMapping
    public ResponseEntity<List<CartItemDto>> viewCart(@PathVariable long customerId) {   //Return a DTO instead of the entity itself. A DTO can be a simplified representation of the cart item with only the necessary information to display to the customer, and it can help reduce unnecessary data exposure.
        List<CartItemDto> cartItemsDtos = cartItemService.getCartItems(customerId);
        return ResponseEntity.ok(cartItemsDtos);     //Return a ResponseEntity with an HTTP status of OK(200) and a list of cart item DTOs (cartItemDtos) as the response body
    }



//Update the quantity of an item in the cart or modify its details.
    @PutMapping("/{itemId}")
    public ResponseEntity<CartItemDto> updateCartItem(
                      @PathVariable long customerId,
                      @PathVariable long itemId,
                      @RequestBody CartItemDto cartItemDto) {       //@RequestBody CartItemDto cartItemDto -> new data for the cart item

        CartItemDto updatedCartItem = cartItemService.updateCartItem(customerId, itemId, cartItemDto);

        if (updatedCartItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(updatedCartItem);
        }
    }
}
