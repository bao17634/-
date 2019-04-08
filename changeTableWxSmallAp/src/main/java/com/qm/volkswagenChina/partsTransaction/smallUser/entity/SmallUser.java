package com.qm.volkswagenChina.partsTransaction.smallUser.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by aquaqu on 2017/6/7.
 */
@Data
public class SmallUser implements Serializable{
    private static final long serialVersionUID = -7425046455066151512L;
    private Long gid = null;
    private String openId = null;
    private String nickName = null;
    private String gender = null;
    private String city = null;
    private String province = null;
    private String country = null;
    private String avatarUrl = null;
    private String unionId = null;
    private String language = null;
    private String session_key = null;
    private String sysUserRealName = null;
    private String lastLoginIp = null;
    private Timestamp lastLoginDate = null;
    private Long sysUserId = null;


}
