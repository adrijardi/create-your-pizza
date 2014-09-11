package com.pizza.controller;

import com.pizza.dao.OrderRepository;
import com.pizza.domain.Pizza;
import com.pizza.domain.PizzaBase;
import com.pizza.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Adrian Lopez on 10/09/2014.
 */
@RestController
@RequestMapping("order")
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Pizza pizza;

    @RequestMapping
    public Page<Topping> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @RequestMapping(value = "base", method = RequestMethod.PUT)
    public void selectBase(PizzaBase pizzaBase) {
        pizza.setPizzaBase(pizzaBase);
    }

    @RequestMapping(value = "topping", method = RequestMethod.PUT)
    public void addTopping(Topping topping) {
        pizza.getToppings().add(topping);
    }

    @RequestMapping(value = "topping", method = RequestMethod.DELETE)
    public void removeTopping(Topping topping) {
        pizza.getToppings().remove(topping);
    }

    @RequestMapping(value = "price", method = RequestMethod.GET)
    public BigDecimal getPrice() {
        return pizza.getTotalPrice();
    }

}
