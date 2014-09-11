package com.pizza.util

import com.pizza.dao.ToppingRepository
import com.pizza.domain.Pizza
import com.pizza.domain.PizzaBase
import com.pizza.domain.Topping
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import spock.lang.Specification

import javax.persistence.EntityManager

class EntityConverterTest extends Specification {

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Matches will match any entity if the parameter is a String or Long"() {
        def converter = new EntityConverter()
        expect:
        converter.matches(source, target) == result

        where:
        source                        | target                                   || result
        createTypeDesc(Long.class)    | createTypeDesc(PizzaBase.class)          || true
        createTypeDesc(String.class)  | createTypeDesc(PizzaBase.class)          || true
        createTypeDesc(String.class)  | createTypeDesc(Topping.class)            || true
        createTypeDesc(Long.class)    | createTypeDesc(Topping.class)            || true
        createTypeDesc(Long.class)    | createTypeDesc(ToppingRepository.class)  || false
        createTypeDesc(Date.class)    | createTypeDesc(Pizza.class)              || false
    }

    TypeDescriptor createTypeDesc(Class<?> clazz) {
        Mock(TypeDescriptor) {
            getType() >> clazz
            getObjectType() >> clazz
        }
    }

    def "GetConvertibleTypes"() {
        def converter = new EntityConverter()
        def types = converter.getConvertibleTypes()

        expect:
        types.contains(new GenericConverter.ConvertiblePair(String.class, PizzaBase.class))
    }

    def "Convert should get the data from the EntityManager"() {
        def converter = new EntityConverter()
        def expected = new PizzaBase(name: "Thin base")
        converter.setEm(Mock(EntityManager) {
            find(PizzaBase.class, 1L) >> expected
        })

        expect:
        converter.convert("1", createTypeDesc(String), createTypeDesc(PizzaBase)) == expected
    }

    def "Convert should set null when the input is empty"() {
        def converter = new EntityConverter()
        def expected = null

        expect:
        converter.convert("", createTypeDesc(String), createTypeDesc(PizzaBase)) == expected
    }

    def "Convert should set null when the Long input is <=0"() {
        def converter = new EntityConverter()
        def expected = null

        expect:
        converter.convert(0L, createTypeDesc(Long), createTypeDesc(PizzaBase)) == expected
    }

    def "Convert should set null when the String input is <=0"() {
        def converter = new EntityConverter()
        def expected = null

        expect:
        converter.convert("-2", createTypeDesc(String), createTypeDesc(PizzaBase)) == expected
    }

    def "Convert should set null when the String input is invalid"() {
        def converter = new EntityConverter()
        def expected = null

        expect:
        converter.convert("gfdgd", createTypeDesc(String), createTypeDesc(PizzaBase)) == expected
    }
}
