package com.pizza.util;

import com.pizza.domain.PizzaBase;
import com.pizza.domain.Topping;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Converts any entity from a string, if the string can be parsed to a long value and the object can be found in the database
 */
public class EntityConverter implements ConditionalGenericConverter {

    @PersistenceContext
    private EntityManager em;

    private static Set<ConvertiblePair> compatibleTypes;

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return hasEntityAnnotation(targetType) && isCompatibleSource(sourceType);
    }

    private boolean isCompatibleSource(TypeDescriptor sourceType) {
        return Number.class.isAssignableFrom(sourceType.getObjectType()) || String.class.isAssignableFrom(sourceType.getObjectType());
    }

    private boolean hasEntityAnnotation(TypeDescriptor targetType) {
        return targetType.getType().getAnnotation(Entity.class) != null;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        if(compatibleTypes == null) {
            synchronized(EntityConverter.class) {
                if(compatibleTypes == null) {
                    compatibleTypes = new HashSet<>();
                    compatibleTypes.add(new ConvertiblePair(String.class, PizzaBase.class));
                    compatibleTypes.add(new ConvertiblePair(Long.class, PizzaBase.class));
                    compatibleTypes.add(new ConvertiblePair(String.class, Topping.class));
                    compatibleTypes.add(new ConvertiblePair(Long.class, Topping.class));
                }
            }
        }
        return compatibleTypes;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Optional<Long> key = getKey(source, sourceType);
        return key.<Object>map(k -> em.find(targetType.getObjectType(), k)).orElse(null);
    }

    private Optional<Long> getKey(Object source, TypeDescriptor sourceType) {
        if(Number.class.isAssignableFrom(sourceType.getObjectType())) {
            return getKey((Number) source);
        }
        else if(String.class.isAssignableFrom(sourceType.getObjectType()))
            return getKey((String) source);
        throw new IllegalArgumentException("Cannot parse an object of type "+sourceType.getType());
    }

    private Optional<Long> getKey(Number key) {
        if(key.longValue() > 0)
            return Optional.of(key.longValue());
        return Optional.empty();
    }

    private Optional<Long> getKey(String key) {
        try {
            return getKey(Long.parseLong(key));
        } catch(NumberFormatException e) {
            return Optional.empty();
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
