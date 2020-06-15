package com.qfjy.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html#UinonId
 * 微信获取个人信息API文档
 */
@Data
public class Weiuser  implements Serializable {

    private Integer id;

    private String openid;

    private String nickname;

    private Integer sex;

    private String city;

    private String country;

    private String province;

    private String language;

    private String headimgurl;

    private String remark;

    // 添加额外字段
    private int subscribe;
    private int subscribe_time;
    private String unionid;
    private int groupid;
    private int[] tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;

 }