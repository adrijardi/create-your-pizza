package com.pizza.dao;

import com.pizza.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Adrian Lopez on 10/09/2014.
 */
public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
