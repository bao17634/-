package com.qm.volkswagenChina.partsTransaction.smallUser.service;

import com.qm.volkswagenChina.partsTransaction.smallUser.dao.ReplenishmentDao;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.MtlVo;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.ReplenishmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplenishmentService {

    @Autowired
    ReplenishmentDao replenishmentDao;

    public void save(ReplenishmentVo vo){
             replenishmentDao.save(vo);
        }
    public List queryByStatusList(String status,String createrId){
        return replenishmentDao.queryByStatusList(status,createrId);
    }
    public int del(String id){
        return replenishmentDao.del(id);
    }
    public int update(String id,String mtlCode,String mtlName,String qty,String recipient,String cause){
        return replenishmentDao.update(id,mtlCode,mtlName,qty,recipient,cause);
    }
    public List queryByStatusWanCList(String status,String mtlCode,String dateStart,String dateEnd,String createrId){
        return replenishmentDao.queryByStatusWanCList(status,mtlCode,dateStart,dateEnd,createrId);
    }
    public List getReplenishmentById(String id){
        return replenishmentDao.getReplenishmentById(id);
    }
    public List queryByStatusWeiWCList(String status,String mtlCode,String dateStart,String dateEnd,String createrId){
        return replenishmentDao.queryByStatusWeiWCList(status,mtlCode,dateStart,dateEnd,createrId);
    }
    public int QuerenUpdateById(String id,String recipient){
        return replenishmentDao.QuerenUpdateById(id,recipient);
    }
    public List<ReplenishmentVo> mtlHistory(){
        return replenishmentDao.mtlHistory();
    }
    public List<MtlVo> mtlQuery(String mtlCode){
        mtlCode = mtlCode.replaceAll(" ", "");
        return replenishmentDao.mtlQuery(mtlCode);
    }
    public List getByIdtoStatus(String id){
        return replenishmentDao.getByIdtoStatus(id);
    }
    public List<MtlVo> QueryByMtlCode(String mtlCode){
        return replenishmentDao.QueryByMtlCode(mtlCode);
    }
    public List shouHuoWCList(String status,String mtlCode,String dateStart,String dateEnd,String recipient){
        return replenishmentDao.shouHuoWCList(status,mtlCode,dateStart,dateEnd,recipient);
    }



}
