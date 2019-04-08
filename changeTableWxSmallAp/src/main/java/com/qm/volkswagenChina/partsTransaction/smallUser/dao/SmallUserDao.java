package com.qm.volkswagenChina.partsTransaction.smallUser.dao;

import com.qm.volkswagenChina.partsTransaction.smallUser.entity.SmallUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 艾纯禹
 * @create 2018-03-24 17:30
 **/
@Mapper
public interface SmallUserDao {
    public void save(SmallUser smallUser);
    public void uploadByOpenId(SmallUser smallUser);
    public SmallUser findByOpenId(String openId);
    public void deleteByOpenId(String openId);
}
