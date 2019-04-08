package com.qm.wxsmall.common.util;


import com.qm.wxsmall.custom.smallUser.entity.UsersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WxSmallKit {
    @Autowired
    HttpServletRequest request;
    @Autowired
    CacheManager cacheManager;

    public UsersVo getSessionWxSmallUser(){
        Object o =  cacheManager.getCache("sessionCache").get(request.getHeader("3rd_jsessionid")).get();
        if (o == null) {
            return null;
        }else{
            return (UsersVo)o;
        }
    }
}
