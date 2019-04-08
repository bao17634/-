package com.qm.volkswagenChina.partsTransaction.smallUser.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.qm.volkswagenChina.common.util.AesKit;
import com.qm.volkswagenChina.common.util.SHA1;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.SmallUser;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.UsersVo;
import com.qm.volkswagenChina.partsTransaction.smallUser.service.SmallUserService;
import com.qm.volkswagenChina.partsTransaction.smallUser.service.UsersService;
import com.qm.yqwl.core.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 艾纯禹
 * @create 2018-03-24 20:31
 **/
@Controller
public class UsersController {
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    private Environment env;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CacheManager cacheManager;
    private static final Log log = LogFactory.get();
    @GetMapping("/wxSmall/loginCheck")
    @ResponseBody
    public Object loginCheck(){
        String code = httpServletRequest.getParameter("code");
        String iv = httpServletRequest.getParameter("iv");
        String encryptedData = httpServletRequest.getParameter("encryptedData");
        String signature = httpServletRequest.getParameter("signature");
        String rawData = httpServletRequest.getParameter("rawData");

        String checkUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code";
        checkUrl = checkUrl.replace("{APPID}", env.getProperty("wxSmall.appid"));
        checkUrl = checkUrl.replace("{SECRET}", env.getProperty("wxSmall.secret"));
        checkUrl = checkUrl.replace("{JSCODE}", code);
        if( log.isDebugEnabled()){
            log.debug("获取到作户登录验证ocde=="+code);
        }

        String str = HttpUtil.get(checkUrl);
        if( log.isDebugEnabled()){
            log.debug("验证用户信息,返回数据=="+str);
        }
        JSONObject jo = JSONObject.parseObject(str);
        String session_key = null;
        String expires_in = null;
        String openid = null;
        if (jo.containsKey("session_key")) {
            session_key = jo.getString("session_key");
        }
        if (jo.containsKey("expires_in")) {
            expires_in = jo.getString("expires_in");
        }
        if (jo.containsKey("openid")) {
            openid = jo.getString("openid");
        }
        String mySignature = SHA1.getSHA1(rawData,session_key);
        if (log.isDebugEnabled()) {
            log.debug("signature==="+signature);
            log.debug("mySignature==="+mySignature);
            log.debug("mySignature eq signature=="+mySignature.equals(signature));
        }
        if(mySignature.equals(signature)){
            if(StringUtils.isNotBlank(expires_in)) {
                httpServletRequest.getSession().setMaxInactiveInterval(Integer.parseInt(expires_in));
            }
            //解密
            try {
                byte[] resultByte = AesKit.instance.decrypt(Base64.decode(encryptedData), Base64.decode(session_key), Base64.decode(iv));
                if (null != resultByte && resultByte.length > 0) {
                    String userInfo = new String(resultByte, "UTF-8");
                    if (log.isDebugEnabled()) {
                        log.debug("解密后userInfo=="+userInfo);
                    }
                    UsersVo vo = new UsersVo();
                    JSONObject json = JSONObject.parseObject(userInfo);
                    String openId = json.getString("openId");
                    vo.setOpenId(openId);
                    if (json.containsKey("nickName")) {
                        vo.setNickName(json.getString("nickName"));
                    }
                    if (json.containsKey("avatarUrl")) {
                        vo.setAvatarUrl(json.getString("avatarUrl"));
                    }
                    if (json.containsKey("gender")) {
                        vo.setGender(json.getString("gender"));
                    }
                    if (json.containsKey("city")) {
                        vo.setCity(json.getString("city"));
                    }
                    if (json.containsKey("province")) {
                        vo.setProvince(json.getString("province"));
                    }
                    if (json.containsKey("country")) {
                        vo.setCountry(json.getString("country"));
                    }

                    if (json.containsKey("language")) {
                        vo.setLanguage(json.getString("language"));
                    }

                    UsersVo rc = usersService.findByOpenId(openId);
                    if(rc==null) {
                        //记录新用户
                        usersService.save(vo);
                        rc = usersService.findByOpenId(openId);
                    }else{
                        usersService.uploadByOpenId(vo);
                        vo.setId(rc.getId());
                    }
                    //登录日志
//                    rc = new Record();
//                    rc.set("gid","WX_SMALL_APP_USER_LOGS_s.nextval");
//                    rc.set("openid",openId);
//                    rc.set("nickName",vo.getNickName());
//                    rc.set("gender",vo.getGender());
//                    rc.set("city",vo.getCity());
//                    rc.set("user_id",vo.getUserId());
//                    rc.set("province",vo.getProvince());
//                    rc.set("country",vo.getCountry());
//                    rc.set("avatarUrl",vo.getAvatarUrl());
//                    rc.set("language",vo.getLanguage());
//                    rc.set("DEALER_CODE",vo.getDealerCode());
//                    rc.set("LOGIN_IP", getRequest().getRemoteAddr());
//                    rc.set("LOGIN_DATE", new Timestamp(new Date().getTime()));
//                    Db.save("WX_SMALL_APP_USER_LOGS", "gid", rc);
                    vo.setSession_key(session_key);
                    Map<String,Object> mp = new HashMap<>();
//                    CacheKit.put("sessionCache",getSession().getId(),vo);
                    mp.put("session_id", httpServletRequest.getSession().getId());
                    mp.put("user_id", rc.getId());
                    mp.put("userName",rc.getUserName());
                    mp.put("tel", rc.getTel());
                    mp.put("type", rc.getType());
//                    renderText(AqucyTools.getOkJson(mp));
                    cacheManager.getCache("sessionCache").put(httpServletRequest.getSession().getId(),vo);
                    return Result.success(mp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.fail("非法访问");
    }


    @GetMapping("/wxSmall/bindUserById")
    @ResponseBody
    public Object bindUserById(@RequestParam(value = "tel") String tel,@RequestParam(value = "userName") String userName,
                               @RequestParam(value = "id") long id){
        UsersVo vo = new UsersVo();
        vo.setId(id);
        vo.setTel(tel);
        vo.setUserName(userName);
        usersService.bindUserById(vo);
        Map<String,Object> jo = new HashMap<>();
        jo.put("tel",tel);
        jo.put("userName",userName);
        return Result.success(jo);
    }




    @GetMapping("/wxSmall/sayHello")
    @ResponseBody
    public Object sayHello(){
        return Result.success("你好世界");
    }
}
