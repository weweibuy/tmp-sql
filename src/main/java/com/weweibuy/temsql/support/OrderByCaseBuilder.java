package com.weweibuy.temsql.support;

import com.weweibuy.temsql.core.OrderByCase;
import com.weweibuy.temsql.core.OrderByTypeEum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author durenhao
 * @date 2021/3/5 11:55
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderByCaseBuilder<T> {

    private List<OrderByCase<T>> orderByList;


    public static <T> OrderByCaseBuilder<T> orderBy(Function<T, Comparable> orderBy, OrderByTypeEum orderByType) {
        OrderByCaseBuilder<T> orderByCaseBuilder = new OrderByCaseBuilder();
        OrderByCase<T> orderByCase = OrderByCase.orderBy(orderBy, orderByType);
        List<OrderByCase<T>> list = new ArrayList<>();
        list.add(orderByCase);
        orderByCaseBuilder.orderByList = list;
        return orderByCaseBuilder;
    }

    public static <T> OrderByCaseBuilder<T> orderBy(Function<T, Comparable> orderBy) {
        return orderBy(orderBy, OrderByTypeEum.ASC);
    }

    public OrderByCaseBuilder<T> and(Function<T, Comparable> orderBy) {
        return and(orderBy, OrderByTypeEum.ASC);
    }

    public OrderByCaseBuilder<T> and(Function<T, Comparable> orderBy, OrderByTypeEum orderByType) {
        OrderByCase<T> orderByCase = OrderByCase.orderBy(orderBy, orderByType);
        orderByList.add(orderByCase);
        return this;
    }


    public List<OrderByCase<T>> build() {
        return orderByList;
    }
}
