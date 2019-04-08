OCR SDK使用说明
===

####1. What's New?
2017年12月30日，OCR SDK1.0.0版本正式发布。

####2. OCR SDK简介
OCR SDK以Jar包形式提供，封装了OCR服务使用AK/SK授权访问的逻辑，简化了使用AK/SK方式访问OCR服务的签名及相关HTTP处理的逻辑，并提供OCR服务调用过程中常用的一些工具函数(如请求上下文构建, 代理方式访问)。

OCR SDK示例代码，以源代码工程的形式提供，主要提供了文字识别类服务的使用范例代码，包括英文海关单据识别、增值税发票识别、身份证识别、驾驶证识别、行驶证识别、通用表格识别和手写字母数字识别服务的使用范例代码。
具体接口参考《[人工智能服务接口信息](http://support.huaweicloud.com/api-ais/ais_03_0014.html "人工智能服务接口信息")》文档(旧版），http://support.huaweicloud.com/api-ocr/ocr_03_0014.html（新版）。

####3. 下载OCR SDK

请到[SDK 下载](http://developer.huaweicloud.com/dev/sdk "SDK 下载")页面下载OCR SDK和示例代码。


####4. 目录结构说明

    +---data
    +---lib
    \---src
        \---com
            \---huawei
                \---ais
                    \---demo
                        +---ocr
    +   ReadMe.md
    +   VERSION
                        
目录说明:

1. src 为示例代码目录
2. data 为示例用数据目录
3. lib 为SDK所在目录，以及示例代码所依赖的基它jar包的路径
4. lib\ais-client-sdk-1.0.1.jar 为OCR的SDK(历史原因，保持名字为ais)
5. ReadMe.md 为OCR SDK使用说明
6. VERSION 为OCR SDK的版本说明文件

####5.OCR SDK使用说明
旧版SDK请下载http://developer.huaweicloud.com/dev/sdk中人工智能服务AIS的SDK