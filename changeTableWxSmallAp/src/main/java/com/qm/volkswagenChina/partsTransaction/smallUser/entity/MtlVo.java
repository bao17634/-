package com.qm.volkswagenChina.partsTransaction.smallUser.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class MtlVo {

    private static final long serialVersionUID = -7425046455066151513L;
    private int id ;
    private String mtlCode = null;
    private String mtlName = null;
    private String kltGlt = null;
    private String area = null;

}
