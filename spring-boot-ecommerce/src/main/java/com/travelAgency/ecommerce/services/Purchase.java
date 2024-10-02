package com.travelAgency.ecommerce.services;

import com.travelAgency.ecommerce.entities.Cart;
import com.travelAgency.ecommerce.entities.CartItem;
import com.travelAgency.ecommerce.entities.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;


}
