package com.qm.wxsmall.common.filter;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.qm.wxsmall.common.util.TokenInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author licy
 */
@Slf4j
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Value("${jwt.signature}")
    private String jwtSignature;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object o
    ) throws Exception {

        String uri = request.getRequestURI();
        if (uri.contains("/ssfError")) {
            return true;
        }
        if (uri.contains("/api/v1/auth/login")) {
            return true;
        }
        if(uri.contains("/wxSmall/")){
            return true;
        }
        if(uri.contains("/demo/hello/vatData1")){
            return true;
        }
        if(uri.contains("demo/hello/search")){
            return true;
        }
        String token = request.getHeader(TokenInfoUtil.TOKEN);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TokenInfoUtil.TOKEN);
            if (!StringUtils.isBlank(token)) {
                token = URLDecoder.decode(token, "utf-8");
            }
        }
        // access_token还是其他
        // 如果判断token为空
        if (StringUtils.isBlank(token)) {
            log.debug("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.addHeader("tokenValid", "false");
            return false;
        }
        try {
            TokenInfoUtil.setToken(token, jwtSignature);
            Long expTime = TokenInfoUtil.getExpTime();
            Date date = new Date(expTime);
            if (date.after(new Date())) {
                return true;
            } else {
                log.info("token过期：{}", token);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.addHeader("tokenValid", "false");
                return false;
            }
        } catch (Exception e) {
            log.info("token验证失败:{}", token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.addHeader("tokenValid", "false");
            return false;
        }
    }

}
