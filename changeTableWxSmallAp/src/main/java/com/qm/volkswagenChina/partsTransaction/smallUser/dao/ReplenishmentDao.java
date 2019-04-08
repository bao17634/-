package com.qm.volkswagenChina.partsTransaction.smallUser.dao;


import com.qm.volkswagenChina.partsTransaction.smallUser.entity.MtlVo;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.ReplenishmentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplenishmentDao {
    public List<ReplenishmentVo> mtlHistory();
    public List<MtlVo> mtlQuery(@Param("mtlCode") String mtlCode);
    int save(ReplenishmentVo vo);
    public List queryByStatusList(@Param("status") String status,@Param("createrId") String createrId);
    public int del(@Param("id") String id);
    public int update(@Param("id") String id,@Param("mtlCode") String mtlCode, @Param("mtlName") String mtlName, @Param("qty") String qty,@Param("recipient") String recipient,@Param("cause") String cause);
    public List queryByStatusWanCList(@Param("status") String status,@Param("mtlCode") String mtlCode,@Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd,@Param("createrId") String createrId);
    public List getReplenishmentById(@Param("id") String id);
    public List queryByStatusWeiWCList(@Param("status") String status,@Param("mtlCode") String mtlCode,@Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd,@Param("createrId") String createrId);
    public int QuerenUpdateById(@Param("id") String id,@Param("recipient") String recipient);
    public List getByIdtoStatus(@Param("id") String id);
    public List<MtlVo> QueryByMtlCode(@Param("mtlCode") String mtlCode);
    public List shouHuoWCList(@Param("status") String status,@Param("mtlCode") String mtlCode,@Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd,@Param("recipient") String recipient);


}
