package com.weweibuy.temsql.core;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author durenhao
 * @date 2021/3/4 23:06
 **/
public class ComposeGroupBy<T> {

    private List<Function<T, Object>> functionList;

    public ComposeGroupBy(List<Function<T, Object>> functionList) {
        this.functionList = functionList;
    }


    public static <T> Collector<T, ?, List<T>> toSortedList(Comparator<T> c) {
        return Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(c)), ArrayList::new);
    }


    public Collector<T, ?, Stream<T>> toCollector(ComposeComparator<T> composeComparator) {
        int size = functionList.size();
        int i = size - 1;
        Collector<T, ?, Stream<T>> collector = null;
        while (i >= 0) {
            if (i == size - 1) {
                if (composeComparator != null) {

                    collector = Collectors.collectingAndThen(Collectors.groupingBy(functionList.get(i),
                            LinkedHashMap::new, toSortedList(composeComparator)),
                            m -> m.values().stream().flatMap(List::stream));
                } else {
                    collector = Collectors.collectingAndThen(Collectors.groupingBy(functionList.get(i),
                            LinkedHashMap::new, Collectors.toList()),
                            m -> m.values().stream().flatMap(List::stream));
                }
            } else {
                collector = Collectors.collectingAndThen(Collectors.groupingBy(functionList.get(i),
                        LinkedHashMap::new, collector),
                        m -> m.values().stream().flatMap(Function.identity()));
            }
            i--;
        }
        return collector;

    }


}
