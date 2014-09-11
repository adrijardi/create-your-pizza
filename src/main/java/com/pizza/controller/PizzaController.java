package com.pizza.controller;

import com.pizza.dao.PizzaBaseRepository;
import com.pizza.dao.ToppingRepository;
import com.pizza.domain.Pizza;
import com.pizza.domain.PizzaBase;
import com.pizza.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PizzaController {

    @Autowired
    private PizzaBaseRepository pizzaBaseRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private Pizza pizza;

    @ModelAttribute("pizzaBases")
    public List<PizzaBase> pizzaBases() {
        return pizzaBaseRepository.findAll();
    }

    @ModelAttribute("toppings")
    public List<Topping> toppings() {
        return toppingRepository.findAll();
    }

    @ModelAttribute("pizza")
    public Pizza pizza() {
        return pizza;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}