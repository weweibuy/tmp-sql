package com.weweibuy.temsql.core;

import java.util.Comparator;
import java.util.function.Function;

/**
 * @author durenhao
 * @date 2021/3/5 11:01
 **/
public class OrderByCase<T> implements Comparator<T> {

    private Function<T, Comparable> function;

    private OrderByTypeEum orderByType;

    private OrderByCase(Function<T, Comparable> function, OrderByTypeEum orderByType) {
        this.function = function;
        this.orderByType = orderByType;
    }

    private OrderByCase(Function<T, Comparable> function) {
        this(function, OrderByTypeEum.ASC);
    }

    @Override
    public int compare(T o1, T o2) {
        Comparable apply1 = function.apply(o1);
        Comparable apply2 = function.apply(o2);
        if (apply1 == null && apply2 != null) {
            return 1;
        }
        if (apply1 == null && apply2 == null) {
            return 0;
        }

        if (apply1 != null && apply2 == null) {
            return -1;
        }

        int i = apply1.compareTo(apply2);
        if (OrderByTypeEum.DESC.equals(orderByType)) {
            return -i;
        }
        return i;
    }


    public static <T> OrderByCase<T> orderBy(Function<T, Comparable> function) {
        return new OrderByCase(function, OrderByTypeEum.ASC);
    }

    public static <T> OrderByCase<T> orderBy(Function<T, Comparable> function, OrderByTypeEum orderByType) {
        return new OrderByCase(function, orderByType);
    }
}
