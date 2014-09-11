package com.pizza.services;

import com.pizza.domain.Pizza;
import com.pizza.domain.PizzaBase;

import javax.transaction.Transactional;

/**
 * Provides functionality to place new orders in the system
 */
public interface OrderService {

    void placeOrder(Pizza pizza);
}
