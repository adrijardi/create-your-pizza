package com.pizza.util;

import java.lang.Object;
import java.util.List;
import java.util.Set;

/**
 * Util class to provide functionality for the Jsp
 */
public final class JspFunctions {

    private JspFunctions(){}

    public static boolean contains(Set<?> set, Object obj) {
        return set.contains(obj);
    }

}
