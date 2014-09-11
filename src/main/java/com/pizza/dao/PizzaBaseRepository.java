package com.pizza.dao;

import com.pizza.domain.PizzaBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Adrian Lopez on 10/09/2014.
 */
public interface PizzaBaseRepository extends JpaRepository<PizzaBase, Long> {
}
