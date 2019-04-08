package com.qm.wxsmall.common.filter;


import com.qm.wxsmall.custom.smallUser.entity.UsersVo;
import com.qm.yqwl.core.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WxSmallInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheManager cacheManager;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if(uri.contains("/wxSmall/")||uri.contains("/error")) {

            if(uri.contains("/wxSmall/loginCheck")){
                return true;
            }
            Cache cache = cacheManager.getCache("sessionCache");
            String sessionid = httpServletRequest.getHeader("3rd_jsessionid");
//            SmallUser user = cache.get(sessionid,SmallUser.class );
            UsersVo user = cache.get(sessionid,UsersVo.class );

            String t_sessionid = null;
            if (user != null) {
                t_sessionid = user.getSession_key();
            }
            if (StringUtils.isBlank(t_sessionid)) {
                httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
                httpServletResponse.getWriter().print(Result.fail("-99"));
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
