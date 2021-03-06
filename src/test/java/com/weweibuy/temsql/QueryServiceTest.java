package com.weweibuy.temsql;

import com.weweibuy.temsql.core.Limit;
import com.weweibuy.temsql.core.OrderByTypeEum;
import com.weweibuy.temsql.support.GroupByBuilder;
import com.weweibuy.temsql.support.OrderByCaseBuilder;
import com.weweibuy.temsql.support.WhereCaseBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QueryServiceTest {

    private List<User> userList = Collections.emptyList();


    @Test
    public void query() throws Exception {

        List<User> resultList = null;
        resultList = QueryService.query(null,
                WhereCaseBuilder.<User>where(u -> u.getId() > 0L).and(u -> u.getId() < 9L).build(),
                OrderByCaseBuilder.orderBy(User::getAge, OrderByTypeEum.DESC).and(User::getId).build(),
                GroupByBuilder.groupBy(User::getDept).andGroupBy(User::getRole).build(),
                Limit.defaultLimit());
        Assert.assertTrue(CollectionUtils.isEmpty(resultList));


        resultList = QueryService.query(userList,
                WhereCaseBuilder.<User>where(u -> u.getId() > 0L).and(u -> u.getId() < 9L).build(),
                OrderByCaseBuilder.orderBy(User::getAge, OrderByTypeEum.DESC).and(User::getId).build(),
                null,
                Limit.defaultLimit());

        Assert.assertTrue(resultList.get(0).getId() == 6L);
        // 排序
        Assert.assertTrue(resultList.get(6).getId() == 5L);

        // 组内 没有年龄排最后
        Assert.assertTrue(resultList.get(7).getId() == 4L);


        resultList = QueryService.query(userList,
                WhereCaseBuilder.<User>where(u -> u.getId() > 0L).and(u -> u.getId() < 9L).build(),
                OrderByCaseBuilder.orderBy(User::getAge, OrderByTypeEum.DESC).and(User::getId).build(),
                GroupByBuilder.groupBy(User::getDept).andGroupBy(User::getRole).build(),
                Limit.defaultLimit());

        // 过滤结果 8条数据
        Assert.assertTrue(resultList.size() == 8);



        Set<Long> collect = resultList.stream().map(User::getId).collect(Collectors.toSet());

        // 过滤结果 不包含 0 与 9
        Assert.assertTrue(!collect.contains(0L) && !collect.contains(9L));

        // 年龄倒序
        Assert.assertTrue(resultList.get(0).getId() == 6L);
        // 年龄相同 id 正序
        Assert.assertTrue(resultList.get(1).getId() == 7L);
        // 组内排序
        Assert.assertTrue(resultList.get(2).getId() == 8L);

        // 分组 组内年龄最大
        Assert.assertTrue(resultList.get(3).getId() == 1L);

        Assert.assertTrue(resultList.get(4).getId() == 5L);

        // 组内 没有年龄排最后
        Assert.assertTrue(resultList.get(5).getId() == 4L);

        // dev leader 组
        Assert.assertTrue(resultList.get(6).getId() == 2L);

        // 无法分组排最后
        Assert.assertTrue(resultList.get(7).getId() == 3L);


    }


    @Before
    public void bf() {

        userList = Arrays.asList(
                User.builder().id(0L).username("0").role("worker").dept("dev").age(40).build(),

                User.builder().id(1L).username("1").role("worker").dept("dev").age(21).build(), // 3
                User.builder().id(2L).username("2").role("leader").dept("dev").age(20).build(), // 6
                User.builder().id(3L).username("3").role("worker").age(15).build(), // 7
                User.builder().id(4L).username("4").role("worker").dept("dev").build(),  // 5
                User.builder().id(5L).username("5").role("worker").dept("dev").age(15).build(), // 4

                User.builder().id(6L).username("6").role("leader").dept("test").age(30).build(), // 0
                User.builder().id(8L).username("8").role("worker").dept("test").age(18).build(), // 1
                User.builder().id(7L).username("7").role("worker").dept("test").age(18).build(), // 2

                User.builder().id(9l).username("9").role("worker").dept("test").age(18).build()
        );


    }

}