package com.qm.volkswagenChina.common.util;


import com.qm.volkswagenChina.partsTransaction.smallUser.entity.SmallUser;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.UsersVo;
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
    public SmallUser getSessionWxCpUser(){
//            Object o =  cacheManager.getCache("sessionCache").get(request.getHeader("3rd_jsessionid")).get();
//            if (o == null) {
//                return null;
//            }else{
//                return (SmallUser)o;
//            }
        return null;
    }
    public UsersVo getSessionWxSmallUser(){
        Object o =  cacheManager.getCache("sessionCache").get(request.getHeader("3rd_jsessionid")).get();
        if (o == null) {
            return null;
        }else{
            return (UsersVo)o;
        }
    }
}
