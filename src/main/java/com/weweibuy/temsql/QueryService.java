package com.weweibuy.temsql;

import com.weweibuy.temsql.core.ComposeComparator;
import com.weweibuy.temsql.core.ComposeGroupBy;
import com.weweibuy.temsql.core.Limit;
import com.weweibuy.temsql.core.OrderByCase;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author durenhao
 * @date 2021/3/3 17:34
 **/
public class QueryService {

    public static <T> List<T> query(List<T> data, List<Predicate<T>> where,
                                    List<OrderByCase<T>> orderBy, List<Function<T, Object>> groupBy, Limit limit) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }

        Stream<T> stream = data.stream();
        // 条件
        if (CollectionUtils.isNotEmpty(where)) {
            for (int i = 0; i < where.size(); i++) {
                stream = stream.filter(where.get(i));
            }
        }

        // 排序
        ComposeComparator<T> composeComparator = null;
        if (CollectionUtils.isNotEmpty(orderBy)) {
            composeComparator = new ComposeComparator<>(orderBy);
            stream = stream.sorted(composeComparator);
        }


        if (CollectionUtils.isEmpty(groupBy)) {
            return stream.skip(limit.offsetOrDefault())
                    .limit(limit.sizeOrDefault())
                    .collect(Collectors.toList());
        }
        // 分组
        ComposeGroupBy<T> composeGroupBy = new ComposeGroupBy<>(groupBy);

        return stream.collect(composeGroupBy.toCollector(composeComparator))
                .skip(limit.offsetOrDefault())
                .limit(limit.sizeOrDefault())
                .collect(Collectors.toList());


    }


}
