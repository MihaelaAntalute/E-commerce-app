package com.spring.ecommerce.service;
//

import com.spring.ecommerce.dto.AddToWishlistDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import com.spring.ecommerce.repository.WishlistItemRepository;
import com.spring.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WishlistItemService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private WishlistItemRepository wishlistItemRepository;

    private WishlistRepository wishlistRepository;

    @Autowired
    public WishlistItemService(ProductRepository productRepository, UserRepository userRepository, WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public Wishlist addItemToWishlist(AddToWishlistDTO addToWishlistDTO) {
        Product foundProduct = productRepository.findById(addToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Wishlist foundWishlist = foundUser.getWishlist();
        foundWishlist.setUser(foundUser);
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(foundProduct);
        wishlistItem.setWishlist(foundWishlist);
        foundWishlist.getWishlistItems().add(wishlistItem);
        return wishlistRepository.save(foundWishlist);
    }

//    public List<Product> getAllProductsByUser(Long userId) {
//        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
//        return productRepository.findAllProductsByUser(foundUser);
//    }

//    public void deleteProductFromWishlist(AddToWishlistDTO addToWishlistDTO){
//        Product foundedProduct = productRepository.findById(addToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
//        User foundUser = userRepository.findById(addToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
//        WishlistItem wishlistItem = new WishlistItem();
//        wishlistItem.setProduct(foundedProduct);
//        wishlistItem.setUser(foundUser);
//        wishlistItemRepository.delete(wishlistItem);
//    }

}
