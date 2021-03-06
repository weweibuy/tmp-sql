package com.weweibuy.temsql.support;

import com.weweibuy.temsql.core.GroupWrapFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author durenhao
 * @date 2021/3/5 13:55
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupByBuilder<T> {

    private List<Function<T, Object>> groupByList;


    public static <T> GroupByBuilder<T> groupBy(Function<T, Object> groupBy) {

        GroupByBuilder groupByBuilder = new GroupByBuilder();
        groupByBuilder.groupByList = new ArrayList();
        groupByBuilder.groupByList.add(new GroupWrapFunction<>(groupBy));
        return groupByBuilder;
    }


    public GroupByBuilder<T> andGroupBy(Function<T, Object> groupBy) {
        groupByList.add(new GroupWrapFunction<>(groupBy));
        return this;
    }


    public List<Function<T, Object>> build() {
        return groupByList;
    }


}
