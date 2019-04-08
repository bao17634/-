package com.qm.volkswagenChina.common.util;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * token工具
 *
 * @author
 */
@Slf4j
public final class TokenInfoUtil {

    /**
     * 用户id
     */
    public static final String TOKEN = "token";
    /**
     * 用户id
     */
    public static final String USER_ID = "user_id";
    /**
     * 律所id
     */
    public static final String OFFICE_ID = "office_id";
    /**
     * 用户名
     */
    public static final String USER_NAME = "user_name";
    /**
     * 律所名称
     */
    public static final String OFFICE_NAME = "office_name";

    /**
     * 过期时间
     */
    public static final String EXPTIME = "exptime";
    /**
     * 设备类型
     */
    public static final String DEVICE_TYPE = "deviceType";
    /**
     * 设备唯一代码
     */
    public static final String DEVICE_CODE = "deviceCode";

    private TokenInfoUtil() {
    }

    /**
     * 本地线程 session
     */
    private static final ThreadLocal<Map<String, Object>> threadLocalMap = new ThreadLocal<>();

    /**
     * 设置 session 信息到本地线程
     */
    public static void setToken(String token, String verifierStr) throws Exception {
        // 解析token
        JWSObject jwsObject = JWSObject.parse(token);
        MACVerifier verifier = new MACVerifier(verifierStr.getBytes());
        if (!jwsObject.verify(verifier)) {
            throw new Exception("token校验失败");
        }

        try {
            JSONObject js = jwsObject.getPayload().toJSONObject();
            String userId = (String) js.get("user_id");
            String officeId = (String) js.get("office_id");
            String userName = (String) js.get("user_name");
            String officeName = (String) js.get("office_name");
            String deviceType = (String) js.get("deviceType");
            String deviceCode = (String) js.get("deviceCode");

            Long expTime = (Long) js.get("exp");

            // 设置值到线程上下文
            Map<String, Object> map = new HashMap<>();
            map.put(TOKEN, token);
            map.put(USER_ID, userId);
            map.put(OFFICE_ID, officeId);
            map.put(USER_NAME, userName);
            map.put(OFFICE_NAME, officeName);
            map.put(EXPTIME, expTime);
            map.put(DEVICE_TYPE, deviceType);
            map.put(DEVICE_CODE, deviceCode);
            threadLocalMap.set(map);
        } catch (Exception e) {
            log.error("token校验失败", e);
            throw new Exception("token校验失败");
        }
    }

    public static String getDeviceType() {
        return (String) threadLocalMap.get().get(DEVICE_TYPE);
    }

    public static String getDeviceCode() {
        return (String) threadLocalMap.get().get(DEVICE_CODE);
    }

    /**
     * 返回线程上线文map信息
     */
    public static Map<String, Object> getCacheMap() {
        return threadLocalMap.get();
    }

    /**
     * 设置上下文tokenCacheMap信息
     */
    public static void setCacheMap(Map<String, Object> cacheMap) {
        threadLocalMap.set(cacheMap);
    }

    /**
     * 获取token信息
     */
    public static String getToken() {
        return (String) threadLocalMap.get().get(TOKEN);
    }

    /**
     * 获取用户id
     */
    public static String getUserId() {
        return (String) threadLocalMap.get().get(USER_ID);
    }

    /**
     * 获取律所Id
     */
    public static String getOfficeId() {
        return (String) threadLocalMap.get().get(OFFICE_ID);
    }

    /**
     * 获取用户名称
     */
    public static String getUserName() {
        return (String) threadLocalMap.get().get(USER_NAME);
    }

    /**
     * 获取office名称
     */
    public static String getOfficeName() {
        return (String) threadLocalMap.get().get(OFFICE_NAME);
    }

    /**
     * 获取office名称
     */
    public static Long getExpTime() {
        return (Long) threadLocalMap.get().get(EXPTIME);
    }

    /**
     * 清除cache信息
     */
    public static void clear() {
        threadLocalMap.remove();
    }

}
