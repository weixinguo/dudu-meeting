package com.qfjy.project.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserInfo
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/11 10:32
 * @Created by Administrator
 */
@Data
public class UserInfo  implements Serializable {
    /**机器人标识 key*/
    private String apiKey;
    /*用户唯一标识*/
    private String userId;
}
