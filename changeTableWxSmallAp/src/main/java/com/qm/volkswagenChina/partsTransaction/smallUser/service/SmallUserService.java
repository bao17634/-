package com.qm.volkswagenChina.partsTransaction.smallUser.service;

import com.qm.volkswagenChina.partsTransaction.smallUser.dao.SmallUserDao;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.SmallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 艾纯禹
 * @create 2018-03-24 19:13
 **/
@Service
public class SmallUserService {
    @Autowired
    SmallUserDao smallUserDao;
    public void save(SmallUser smallUser){
        smallUserDao.save(smallUser);
    }
    public void uploadByOpenId(SmallUser smallUser){
        smallUserDao.uploadByOpenId(smallUser);
    }
    public SmallUser findByOpenId(String openId){
        return smallUserDao.findByOpenId(openId);
    }
    public void deleteByOpenId(String openId){
        smallUserDao.deleteByOpenId(openId);
    }
}
