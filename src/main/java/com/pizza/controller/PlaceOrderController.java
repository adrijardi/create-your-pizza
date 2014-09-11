package com.pizza.controller;

import com.pizza.domain.Pizza;
import com.pizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Provides endpoints for the user to purchase pizzas
 */
@Controller
@RequestMapping("order")
public class PlaceOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Pizza pizza;

    @RequestMapping(value = "buy", method = RequestMethod.POST)
    public String placeOrder() {
        orderService.placeOrder(pizza);
        return "redirect:/";
    }
}
