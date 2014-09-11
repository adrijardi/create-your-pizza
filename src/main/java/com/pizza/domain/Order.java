package com.pizza.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Adrian Lopez on 10/09/2014.
 */
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizzaBase_id")
    private PizzaBase pizzaBase;

    @ManyToMany
    @JoinTable(name = "Design_Topping", joinColumns = @JoinColumn(name = "design_id"), inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private Set<Topping> toppings;

    private BigDecimal price;

    Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
