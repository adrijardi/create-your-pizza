package com.pizza.domain;

import java.math.BigDecimal;

/**
 * Interfaz to implement by the objects that can be defined a price
 */
public interface PricedSupport {

    public void setPrice(BigDecimal price);
}

