package com.qm.volkswagenChina.partsTransaction.smallUser.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContactUsDao {

    int contactUsSave(@Param("content") String contents,
                      @Param("createBy") String createBy,
                      @Param("lastModifyBy") String lastModifyBy);

}