package com.travelAgency.ecommerce.services;

import com.travelAgency.ecommerce.dao.CartRepository;
import com.travelAgency.ecommerce.dao.CustomerRepository;
import com.travelAgency.ecommerce.entities.Cart;
import com.travelAgency.ecommerce.entities.CartItem;
import com.travelAgency.ecommerce.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    private CartRepository cartRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve the customer info from dto
        Customer customer = purchase.getCustomer();

        //retrieve the cart info from dto
        Cart cart = purchase.getCart();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //populate cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));
        //cart.setCartItem(purchase.getCartItems());

        //populate customer with cart
        customer.add(cart);

        cart.setStatus(Cart.StatusType.ordered);

        //save to the database
        //customerRepository.save(customer);
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);


    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number
        return UUID.randomUUID().toString();
    }
}
