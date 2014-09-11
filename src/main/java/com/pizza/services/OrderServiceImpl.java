package com.pizza.services;

import com.pizza.domain.Pizza;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Provides functionality to place new orders in the system
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Override
    public void placeOrder(Pizza pizza) {
        // TODO save Order on the system
        pizza.setPizzaBase(null);
        pizza.getToppings().clear();
    }
}
