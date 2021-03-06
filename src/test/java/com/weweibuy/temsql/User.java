package com.weweibuy.temsql;

import lombok.Builder;
import lombok.Data;

/**
 * @author durenhao
 * @date 2021/3/3 17:30
 **/
@Data
@Builder
public class User {

    private Long id;

    private String username;

    private String dept;

    private String role;

    private Integer age;


}
