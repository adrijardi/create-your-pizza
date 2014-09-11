package com.pizza.domain

import spock.lang.Specification

/**
 * Unit tests to check the login on the pizza price calculation
 */
class PizzaTest extends Specification {

    def "GetTotalPrice must return 0 when no base or toppings are selected"() {
        def pizza = new Pizza()

        expect:
        pizza.getTotalPrice() == 0
    }

    def "GetTotalPrice must return the sum of the prices of the base and toppings"() {
        setup:
        def pizza = new Pizza(
                pizzaBase: Mock(PizzaBase) {
                    getPrice() >> 3.00
                },
                toppings: [
                    Mock(Topping) {
                        getPrice() >> 1.5
                    },
                    Mock(Topping) {
                        getPrice() >> 0.75
                    }
                ])
        expect:
        pizza.getTotalPrice() == 5.25
    }
}
