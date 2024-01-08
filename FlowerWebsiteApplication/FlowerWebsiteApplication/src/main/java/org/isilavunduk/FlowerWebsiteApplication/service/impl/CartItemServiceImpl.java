package org.isilavunduk.FlowerWebsiteApplication.service.impl;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CartItemDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerDto;
import org.isilavunduk.FlowerWebsiteApplication.exception.ResourceNotFoundException;
import org.isilavunduk.FlowerWebsiteApplication.model.CartItem;
import org.isilavunduk.FlowerWebsiteApplication.repository.CartItemRepository;
import org.isilavunduk.FlowerWebsiteApplication.repository.CustomerRepository;
import org.isilavunduk.FlowerWebsiteApplication.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


//Controller Class Methods are implemented in this class
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, CustomerRepository customerRepository){
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public CartItemDto addItemToCart(long customerId, CartItemDto cardItemDto)
    {
        return new CartItemDto();
    }



    //Retrieves all cart items for a specific customer and converts them into DTOs
    @Override
    public List<CartItemDto> getCartItems(long customerId) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);    //Get all cart items associated with the given customerId from DB
        List<CartItemDto> cartItemDtos = new ArrayList<>();         //initializes cartItemDtos with a new instance of ArrayList(resizable-array implementation of the List interface and I used here to store and manage a collection of CartItemDto objects)
        for (CartItem cartItem : cartItems) {
            CartItemDto dto = mapToDto(cartItem);      //calls mapToDto method to convert each CartItem into a CartItemDto
            cartItemDtos.add(dto);          //adds the converted DTO to a list of DTOs.
        }
        return cartItemDtos;
    }


    @Override
    public boolean removeItemFromCart(long customerId, long itemId) {
        return true;
    }

    @Override
    public boolean removeChocolateFromCart(long customerId, long chocolateId) {
        return false;
    }

    @Override
    public CartItemDto addChocolateToCart(long customerId, ChocolateDto chocolateDTO) {
        return null;
    }

    @Override
    public CartItemDto addFlowerToCart(long customerId, FlowerDto flowerDTO) {
        return null;
    }


    //Find the existing cart item using itemId and customerId, update its details, save the updated item,
    //And then return the updated CartItemDto.
    @Override
    public CartItemDto updateCartItem(long customerId, long itemId, CartItemDto cartItemDto) {

        //Find the existing cart item
        CartItem cartItem = cartItemRepository.findByCustomerIdAndItemId(customerId, itemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "itemId", itemId));

        // Update quantity
        cartItem.setQuantity(cartItemDto.getQuantity());

        // Save the updated cart item
        CartItem updatedCartItem = cartItemRepository.save(cartItem);

        // Return the updated CartItemDto
        return mapToDto(updatedCartItem);
    }



    //Convert a CartItem entity into a CartItemDto. Separates data layer(CartItem) from the API layer
    private CartItemDto mapToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        return cartItemDto;
    }

}
