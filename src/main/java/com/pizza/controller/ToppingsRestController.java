package com.pizza.controller;

import com.pizza.dao.ToppingRepository;
import com.pizza.domain.Pizza;
import com.pizza.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Rest controller providing the topping data
 */
@RestController
@RequestMapping("topping")
public class ToppingsRestController {

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private Pizza pizza;

    /*
    This code is actually never used, however it would be a good way to consume that data using Ajax
     */
    @RequestMapping(method = GET)
    public Map<Topping, Boolean> getAvailableToppings() {
        final List<Topping> all = toppingRepository.findAll();
        Map<Topping, Boolean> areSelected = new HashMap<>((int)((float)all.size() / 0.75)+1);
        all.forEach(topping -> areSelected.put(topping, pizza.getToppings().contains(topping)));
        return areSelected;
    }
}
