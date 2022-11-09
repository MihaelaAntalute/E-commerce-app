package com.spring.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    public User() {
    }

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<CartItem> cartItems;
//Un user are un wishlist (deci one-to-one intre user si wishlist)

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Wishlist wishlists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
