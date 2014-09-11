package com.pizza.domain;

import java.math.BigDecimal;

/**
 * Created by Adrian Lopez on 10/09/2014.
 */
public interface Priced {

    public BigDecimal getPrice();

    public void setPrice(BigDecimal price);
}

