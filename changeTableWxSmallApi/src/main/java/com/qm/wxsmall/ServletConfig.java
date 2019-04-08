package com.qm.wxsmall;


import com.qm.yqwl.core.ApplicationServletInitializer;

/**
 * 使用外置tomcat时需要
 * @author by licy
 *
 */
public class ServletConfig extends ApplicationServletInitializer {

    @Override
    protected Class<?> getAppClass() {
        return Application.class;
    }

}
