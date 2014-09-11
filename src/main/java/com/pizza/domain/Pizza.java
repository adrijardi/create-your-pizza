package com.pizza.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.math.BigDecimal.ZERO;

/**
 * Defines the components of a pizza object
 */
public class Pizza {

    private PizzaBase pizzaBase;
    private Set<Topping> toppings = new HashSet<>();

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }

    public PizzaBase getPizzaBase() {
        return pizzaBase;
    }

    public void setPizzaBase(PizzaBase pizzaBase) {
        this.pizzaBase = pizzaBase;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal toppingPrice = getToppings().stream().map(Topping::getPrice).reduce(ZERO, (a,b) -> a.add(b));
        return toppingPrice.add(Optional.ofNullable(getPizzaBase()).map(PizzaBase::getPrice).orElse(ZERO));
    }
}
