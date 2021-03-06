package com.weweibuy.temsql.core;

import lombok.Setter;

import java.util.Optional;

/**
 * @author durenhao
 * @date 2021/3/3 17:47
 **/
@Setter
public class Limit {

    public static final Integer DEFAULT_LIMIT_SIZE = 10;

    public static final Long DEFAULT_OFFSET = 0L;

    private Long offset;

    private Integer size;


    public static Limit defaultLimit() {
        return withOffsetAndSize(DEFAULT_OFFSET, DEFAULT_LIMIT_SIZE);
    }

    public static Limit withSize(Integer size) {
        return withOffsetAndSize(DEFAULT_OFFSET,size);
    }

    public static Limit withOffsetAndSize(Long offset, Integer size) {
        Limit limit = new Limit();
        limit.setOffset(offset);
        limit.setSize(size);
        return limit;
    }

    public Long offsetOrDefault() {
        return Optional.ofNullable(offset)
                .orElse(DEFAULT_OFFSET);
    }

    public Integer sizeOrDefault() {
        return Optional.ofNullable(size)
                .orElse(DEFAULT_LIMIT_SIZE);
    }

}
