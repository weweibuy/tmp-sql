package com.weweibuy.temsql.core;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @author durenhao
 * @date 2021/3/4 22:45
 **/
public class ComposeComparator<T> implements Comparator<T> {

    private List<OrderByCase<T>> orderByCaseList;

    public ComposeComparator(List<OrderByCase<T>> orderByCaseList) {
        this.orderByCaseList = orderByCaseList;
    }

    @Override
    public int compare(T o1, T o2) {
        if (CollectionUtils.isEmpty(orderByCaseList)) {
            return 0;
        }
        int i = 0;
        while (i < orderByCaseList.size()) {
            OrderByCase<T> orderByCase = orderByCaseList.get(i);
            int v = orderByCase.compare(o1, o2);
            if (v != 0) {
                return v;
            }
            i++;
        }
        return 0;
    }
}
