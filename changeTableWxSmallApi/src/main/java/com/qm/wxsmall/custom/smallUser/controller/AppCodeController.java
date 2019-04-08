package com.qm.wxsmall.custom.smallUser.controller;

import com.qm.wxsmall.common.orc.APPCodeDemo;
import com.qm.wxsmall.common.orc.HTMLTOExcel;
import com.qm.yqwl.core.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class AppCodeController {
    int DataName=00001;
    @Autowired
    HttpServletRequest httpServletRequest;
    @RequestMapping(value ="/wxSmall/code/file", method = RequestMethod.POST)
    @ResponseBody
    public Result<net.sf.json.JSONObject> appCCode(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        APPCodeDemo appCodeDemo=new APPCodeDemo();
        if (file.isEmpty()) {
            return Result.fail("文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.debug("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = httpServletRequest.getSession().getServletContext().getRealPath("uploads/");
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        String re = "/uploads/"+fileName;

        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            System.out.println("2=="+(System.currentTimeMillis()-startTime));
            startTime =System.currentTimeMillis();
            System.out.println(re);
            String json = appCodeDemo.appCode(filePath + fileName);

            System.out.println("3=="+(System.currentTimeMillis()-startTime));


            net.sf.json.JSONObject Json = net.sf.json.JSONObject.fromObject(json);
            //获取json中的字符串
            String jsonArray = Json.getString("tables");
            System.out.println("这是解析结果哦");
            System.out.println(jsonArray);
            System.out.println("看是否为空");
            System.out.println(jsonArray.indexOf("</table>"));
            System.out.println(jsonArray.indexOf("<div id=\"margin_0\" class=\"margin_txt\">"));
            if((jsonArray.indexOf("</table>")>=0)&&(jsonArray.indexOf("<div id=\"margin_0\" class=\"margin_txt\">")>=0)){
            //把不能识别的字符串去掉
            String jsonTable = jsonArray.substring(jsonArray.indexOf("<div id=\"margin_0\" class=\"margin_txt\">"), jsonArray.indexOf("</table>"));
            System.out.println("这是第二次解析哦");
            System.out.println(jsonTable);
            System.out.println("这是json数据");
            System.out.println(Json);
            return Result.success(jsonTable);
            }else {
                return Result.fail("识别失败");

            }

        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail("文件上传失败");
    }
    @PostMapping("/wxSmall/excel/importExcel")
    @ResponseBody
    public String importExcel (@RequestParam(value = "dataExcel") String dataExcel) throws IOException {
        java.util.Date  date=new java.util.Date();
        System.out.println(dataExcel);
        int number=(int)Math.random()*10;
        java.sql.Date  data=new java.sql.Date(date.getTime());
        String excelName=data+""+String.valueOf(number);
        String fileName=excelName+".xls";
        HTMLTOExcel htmltoExcel=new HTMLTOExcel();
        htmltoExcel.toExcel(httpServletRequest,dataExcel,excelName);
        return fileName;
    }
}
