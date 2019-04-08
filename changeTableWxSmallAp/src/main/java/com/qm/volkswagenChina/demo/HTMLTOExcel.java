package com.qm.volkswagenChina.demo;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.servlet.http.HttpServletRequest;

///COPY BY  安卓无忧
public class HTMLTOExcel {

	//得到Document并且设置编码格式
	public Document getDoc(String htmlName) throws IOException{
		System.out.println(htmlName);
		Document doc= Jsoup.parse(htmlName);
		return doc;
	}

	///这个方法用于根据trs行数和sheet画出整个表格
	public void mergeColRow(Elements trs,WritableSheet sheet) throws RowsExceededException, WriteException{
		int[][] rowhb=new int[300][50];
		for(int i=0;i<trs.size();i++){
			Element tr=trs.get(i);
			Elements tds=tr.getElementsByTag("td");

			int realColNum=0;
			for(int j=0;j<tds.size();j++){
				Element td=tds.get(j);
				if(rowhb[i][realColNum]!=0){
					realColNum=getRealColNum(rowhb,i,realColNum);
				}
				int rowspan=1;
				int colspan=1;
				if(td.attr("rowspan")!=""){
					rowspan = Integer.parseInt(td.attr("rowspan"));
				}
				if(td.attr("colspan")!=""){
					colspan = Integer.parseInt(td.attr("colspan"));
				}
				String text=td.text();
				drawMegerCell(rowspan,colspan,sheet,realColNum,i,text,rowhb);
				realColNum=realColNum+colspan;
			}

		}
	}
	///这个方法用于根据样式画出单元格，并且根据rowpan和colspan合并单元格
	public void drawMegerCell(int rowspan,int colspan,WritableSheet sheet,int realColNum,int realRowNum,String text,int[][] rowhb) throws RowsExceededException, WriteException{

		for(int i=0;i<rowspan;i++){
			for(int j=0;j<colspan;j++){
				if(i!=0||j!=0){
					text="";
				}
				Label label = new Label(realColNum+j,realRowNum+i,text);
				WritableFont countents = new WritableFont(WritableFont.TIMES,10); // 设置单元格内容，字号12
				WritableCellFormat cellf = new WritableCellFormat(countents );
				cellf.setAlignment(jxl.format.Alignment.CENTRE);//把水平对齐方式指定为居中
				cellf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居
				label.setCellFormat(cellf);
				sheet.addCell(label);
				rowhb[realRowNum+i][realColNum+j]=1;
			}
		}
		sheet.mergeCells(realColNum,realRowNum, realColNum+colspan-1,realRowNum+rowspan-1);
	}
	public  int getRealColNum(int[][] rowhb,int i,int realColNum){
		while(rowhb[i][realColNum]!=0){
			realColNum++;
		}
		return realColNum;
	}
	///根据colgroups设置表格的列宽
	public void setColWidth(Elements colgroups,WritableSheet sheet){
		if(colgroups.size()>0){
			Element colgroup=colgroups.get(0);
			Elements cols=colgroup.getElementsByTag("col");
			for(int i=0;i<cols.size();i++){
				Element col=cols.get(i);
				String strwd=col.attr("width");
				if(col.attr("width")!=""){
					int wd=Integer.parseInt(strwd);
					sheet.setColumnView(i,wd/8);
				}

			}

		}
	}
	//toExcel是根据html文件地址生成对应的xls
	public  String toExcel(HttpServletRequest httpServletRequest,String htmlName,String excelName)throws IOException{

		Document doc=getDoc(htmlName);
		System.out.println("sdsjidnisnsiidninisdn");
		System.out.println(doc);
		String title = doc.title();
		//得到样式，以后可以根据正则表达式解析css，暂且没有找到cssparse
		Elements style= doc.getElementsByTag("style");
		//得到Table，demo只演示输入一个table，以后可以用循环遍历tables集合输入所有table
		Elements tables= doc.getElementsByTag("TABLE");
		if(tables.size()==0){
			return null;
		}
		Element table=tables.get(0);
		//得到所有行
		Elements trs = table.getElementsByTag("tr");
		///得到列宽集合
		Elements colgroups=table.getElementsByTag("colgroup");

		try {
			//文件保存到指定目录下
			String path=httpServletRequest.getSession().getServletContext().getRealPath("Excel/");
			path+=excelName+".xls";
			System.out.println(path);
			WritableWorkbook book = Workbook.createWorkbook(new File(path));
			WritableSheet sheet = book.createSheet("人事关系", 0);
			setColWidth(colgroups,sheet);
			mergeColRow(trs,sheet);
			book.write();
			book.close();
			return path;
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}


}