package org.isilavunduk.FlowerWebsiteApplication.service;
import org.isilavunduk.FlowerWebsiteApplication.DTO.CartItemDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.ChocolateDto;
import org.isilavunduk.FlowerWebsiteApplication.DTO.FlowerDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CartItemService {
    CartItemDto addItemToCart(long customerId, CartItemDto cartItemDto);

    CartItemDto updateCartItem(long customerId, long itemId, CartItemDto cartItemDto);

    boolean removeItemFromCart(long customerId, long itemId);

    boolean removeChocolateFromCart(long customerId, long chocolateId);

    CartItemDto addChocolateToCart(long customerId, ChocolateDto chocolateDto);

    CartItemDto addFlowerToCart(long customerId, FlowerDto flowerDto);

    List<CartItemDto> getCartItems(long customerId);
}
