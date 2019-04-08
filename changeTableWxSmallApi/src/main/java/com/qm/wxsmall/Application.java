package com.qm.wxsmall;

import com.qm.wxsmall.common.util.DynamicDataSource.DynamicDataSourceRegister;
import com.qm.yqwl.core.log.EnableJsonLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJsonLogger
@EnableTransactionManagement
@EnableCaching
@Import({DynamicDataSourceRegister.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}