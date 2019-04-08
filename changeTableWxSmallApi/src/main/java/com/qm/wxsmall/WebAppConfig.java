package com.qm.wxsmall;

import com.qm.wxsmall.common.filter.LoginHandlerInterceptor;
import com.qm.wxsmall.common.filter.WxSmallInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lzp
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LoginHandlerInterceptor getLoginHandlerInterceptor() {
        return new LoginHandlerInterceptor();
    }
    @Bean
    public WxSmallInterceptor getWxcpInterceptor(){
        return  new WxSmallInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(getLoginHandlerInterceptor())
                .addPathPatterns("*")
                .excludePathPatterns(
                        "/swagger-resources*",
                        "/v2/api-docs",
                        "/index.html");
        registry.addInterceptor(getWxcpInterceptor());
        super.addInterceptors(registry);

    }
}
