package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem,Long> {


}
