package com.qm.volkswagenChina.partsTransaction.smallUser.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ReplenishmentVo {

    private static final long serialVersionUID = -7425046455066151512L;
    private int id ;
    private String mtlCode = null;
    private String mtlName = null;
    private String qty = null;
    private String recipient = null;
    private String cause = null;
    private Date yhTime = null;
    private Date shTime = null;
    private String createrId = null;
    private String status = null;
    private String createrName = null;
    private String recipientName = null;
  //  private Date
}
