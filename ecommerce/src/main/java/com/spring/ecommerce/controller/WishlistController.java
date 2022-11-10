package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddToWishlistDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;

import com.spring.ecommerce.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistItemService wishlistItemService;

    @Autowired
    public WishlistController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }

    @PostMapping("/add")
    public Wishlist addToWishlist(@RequestBody AddToWishlistDTO addToWishlistDTO) {
        return wishlistItemService.addItemToWishlist(addToWishlistDTO);
    }


//    @GetMapping("/{userId}")
//    public List<Product> getAllProductsByUser(@PathVariable Long userId) {
//        return wishlistItemService.getAllProductsByUser(userId);
//    }

//    @DeleteMapping("/delete")
//    public void deleteProductFromWishlist(@RequestBody AddToWishlistDTO addToWishlistDTO){
//        wishlistItemService.deleteProductFromWishlist(addToWishlistDTO);
//    }

}
