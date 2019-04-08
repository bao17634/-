package com.qm.wxsmall.custom.smallUser.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by aquaqu on 2017/6/7.
 */
@Data
public class UsersVo implements Serializable{
    private static final long serialVersionUID = -7425046455066151518L;
    private Integer id = null;
    private String nickName = null;
    private String avatarUrl = null;
    private String gender = null;
    private String city = null;
    private String province = null;
    private String country = null;
    private String language = null;
    private String openId = null;
    private String tel = null;
    private String userName = null;
    private String type = null;
    private String status = null;
    private String session_key = null;


}
