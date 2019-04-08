package com.qm.volkswagenChina.partsTransaction.smallUser.dao;

import com.qm.volkswagenChina.partsTransaction.smallUser.entity.SmallUser;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.UsersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 艾纯禹
 * @create 2018-03-24 17:30
 **/
@Mapper
public interface UsersDao {
    public void save(UsersVo user);
    public void uploadByOpenId(UsersVo user);
    public UsersVo findByOpenId(String openId);
    public void deleteByOpenId(String openId);
    public void bindUserById(UsersVo user);
    public UsersVo getCreaterName( String createrId);
    public UsersVo getRecipientName( String createrId);

}
