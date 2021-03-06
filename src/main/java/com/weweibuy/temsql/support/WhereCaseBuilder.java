package com.weweibuy.temsql.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author durenhao
 * @date 2021/3/5 11:49
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WhereCaseBuilder<T> {

    private List<Predicate<T>> predicateList;

    public static <T> WhereCaseBuilder<T> where(Predicate<T> predicate) {
        WhereCaseBuilder whereCaseBuilder = new WhereCaseBuilder();
        whereCaseBuilder.predicateList = new ArrayList();
        whereCaseBuilder.predicateList.add(predicate);
        return whereCaseBuilder;
    }


    public WhereCaseBuilder<T> and(Predicate<T> predicate) {
        predicateList.add(predicate);
        return this;
    }


    public List<Predicate<T>> build() {
        return predicateList;
    }

}
