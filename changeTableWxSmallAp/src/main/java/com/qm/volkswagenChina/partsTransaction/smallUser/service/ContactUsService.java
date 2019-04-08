package com.qm.volkswagenChina.partsTransaction.smallUser.service;


import com.qm.volkswagenChina.partsTransaction.smallUser.dao.ContactUsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {

    @Autowired
    ContactUsDao contactUsDao;
    public int contactUsSave(String content,
                             String createBy,
                             String lastModifyBy){
        return contactUsDao.contactUsSave(content,createBy,lastModifyBy);
    }
}
