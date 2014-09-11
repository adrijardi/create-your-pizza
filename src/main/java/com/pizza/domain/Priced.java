package com.pizza.domain;

import java.math.BigDecimal;

/**
 * Interface to implement by the objects that have a defined price
 */
public interface Priced {

    public BigDecimal getPrice();

}

