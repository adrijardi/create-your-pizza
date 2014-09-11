package com.pizza.controller;

import com.pizza.domain.Pizza;
import com.pizza.domain.PizzaBase;
import com.pizza.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Provides endpoints for the current order
 */
@RestController
@RequestMapping("order")
public class OrderRestController {

    @Autowired
    private Pizza pizza;

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
