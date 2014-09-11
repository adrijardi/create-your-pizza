package com.pizza.dao;

import com.pizza.domain.PizzaBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data jpa Repository for the Pizza bases
 */
public interface PizzaBaseRepository extends JpaRepository<PizzaBase, Long> {
}
