package com.weweibuy.temsql.core;

import java.util.function.Function;

/**
 * @author durenhao
 * @date 2021/3/6 11:22
 **/
public class GroupWrapFunction<T> implements Function<T, Object> {

    private static final Object DEFAULT_OBJECT = new Object();

    private Function<T, Object> groupBy;

    public GroupWrapFunction(Function<T, Object> groupBy) {
        this.groupBy = groupBy;
    }

    @Override
    public Object apply(T t) {
        Object apply = groupBy.apply(t);
        if (apply == null) {
            return DEFAULT_OBJECT;
        }
        return apply;
    }
}
