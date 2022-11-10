package com.spring.ecommerce.service;

import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.OrderItem;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.OrderRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartItemService cartItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartItemService cartItemService) {
        this.cartItemService = cartItemService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    //metoda de tip tranzactie
    public Order placeOrder(Long userId) {
        //1.1cautam utilizatorul dupa id
        //1.2luam toate cartItem-urile de la utilizator
        //3.cream cate un orderItem pt fiecare cartItem
        //4. adaugam orderItem-ul la order
        //5. salvam order-ul in baza de date
        //6. stergem toate cart-itemurile utilizatorului din baza de date
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        List<CartItem> userCartItems = foundUser.getCartItems();
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setUser(foundUser);
        newOrder.setTotalPrice(cartItemService.computeTotalPrice(userCartItems));
        for (CartItem cartItem : userCartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(newOrder);
            newOrder.getOrderItemList().add(orderItem);
        }
        Order savedOrder = orderRepository.save(newOrder);
        cartItemService.deleteAllUserCartItems(userId);
        //cartItemService.deleteAllByUserId(userId);
        return savedOrder;
    }

    public List<Order> getAllOrdersByUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return orderRepository.findAllByUserOrderByCreatedDateDesc(foundUser);
    }

    public Order getOrderDetails(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
        return foundOrder;
    }



}
