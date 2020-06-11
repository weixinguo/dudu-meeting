package com.qfjy.project.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname TulingBean
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/11 10:28
 * @Created by Administrator
 */
@Data
public class TulingBean implements Serializable {
     /**输入类型:0-文本(默认)、1-图片、2-音频*/
    private int reqType=0;
    /**输入参数 */
    private Perception perception;
    /**用户信息 */
    private UserInfo userInfo;
}

