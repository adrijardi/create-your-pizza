package com.pizza.dao;

import com.pizza.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data jpa Repository for the toppings
 */
public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
