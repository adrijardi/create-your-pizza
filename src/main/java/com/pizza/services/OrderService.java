package com.pizza.services;

import com.pizza.domain.Pizza;

/**
 * Provides functionality to place new orders in the system
 */
public interface OrderService {

    void placeOrder(Pizza pizza);
}
