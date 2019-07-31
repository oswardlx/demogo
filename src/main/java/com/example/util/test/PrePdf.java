package com.example.util.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.OutputStream;
import java.util.Iterator;

@Service
public class PrePdf {
    public static void main(String[] args) throws Exception {
//        createPDF();
        String testJson = "{\"id\":101,\"pId\":101,\"isParent\":false,\"name\":\"块\",\"ename\":\"Chunk\",\"level\":4,\"tId\":\"treeDemo_9\",\"parentTId\":\"treeDemo_8\",\"open\":false,\"zAsync\":true,\"isFirstNode\":true,\"isLastNode\":true,\"isAjaxing\":false,\"checked\":false,\"checkedOld\":false,\"nocheck\":false,\"chkDisabled\":false,\"halfCheck\":false,\"check_Child_State\":-1,\"check_Focus\":false,\"isHover\":true,\"editNameFlag\":false,\"text\":\"zzzz\",\"parameter\":\"{}\",\"fontBase\":\"xx\",\"fontSize\":\"7\",\"fontStyle\":\"0\"}";
        JSONObject jo = JSONObject.parseObject(testJson);
//        if(jo.get())
        System.out.println(jo.toJSONString());
    }

    public void createPDF(OutputStream outputStream) throws Exception {
        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);
        //设置横向
        rect = rect.rotate();

//        rect.setBackgroundColor(BaseColor.RED);
//        rect.getLeft()
        //创建文档实例
        Document doc = new Document(rect);
        doc.setMargins(12, 12, 50, 12);

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //设置字体样式
        Font textFont = new Font(bfChinese, 11, Font.NORMAL); //正常


        //创建输出流
        PdfWriter.getInstance(doc, outputStream);

        doc.open();
//        doc.newPage();

        //块
        Chunk c1 = new Chunk("*韩国计划*", textFont);
        doc.add(c1);

        //段落
        Paragraph p1 = new Paragraph();
        //短语
        Phrase ph1 = new Phrase();
        //块
        Chunk c2 = new Chunk("*********", textFont);
        Chunk c11 = new Chunk("（信用报告提供机构l ogo）", textFont);
        //将块添加到短语
        ph1.add(c2);
        ph1.add(c11);
        //将短语添加到段落
        p1.add(ph1);
        p1.setSpacingBefore(100);
        //将段落添加到短语
        PdfPTable table3 = new PdfPTable(2);
        table3.setWidthPercentage(100f);
        PdfPCell celltempr;
        celltempr = new PdfPCell(p1);
        table3.addCell(celltempr);
        table3.completeRow();
        doc.add(table3);


        // 创建一个有4列的表格
        PdfPTable table = new PdfPTable(4);
//        table.setTotalWidth(new float[]{ 105, 170, 105, 170 }); //设置列宽
        table.setWidths(new float[]{105, 170, 105, 170});
        table.setWidthPercentage(100);
//        table.setLockedWidth(true); //锁定列宽
        PdfPCell celltemp0;
        Paragraph paragraph = new Paragraph(56, "第三方第三方第三方师傅师傅对方水电费是范德萨发生水电费水电费是", textFont);
//        paragraph.setFont(textFont);
//        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph.setFirstLineIndent(23);
        paragraph.setLeading(4);
        celltemp0 = new PdfPCell(paragraph);
        celltemp0.setFixedHeight(10);
//        celltemp0.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        PdfPCell celltemp;
        celltemp = new PdfPCell(new Phrase("企业名称", textFont));
        celltemp.setFixedHeight(15);
//        celltemp.setPadding();
        table.addCell(celltemp0);
//        Image img  = new Image() {
//        }
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
        table.addCell(celltemp);
//        celltemp.setRotation(-90);
        table.addCell(celltemp);
        table.completeRow();
//        Paragraph ph = new Paragraph("fff",textFont);
//        table.addCell(ph);
//        PdfPCell cell;
//        cell = new PdfPCell(new Phrase("企业名称", textFont));
//        cell.setBorderWidthLeft(3);
//        cell.setBorderWidthTop(3);
////        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setMinimumHeight(30); //设置单元格高度
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell();
//        cell.setBorderWidthRight(3);
//        cell.setBorderWidthTop(3);
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        cell.setColspan(3);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("统一社会信用代码（hgjfgdfghfjhfjfghjfjhfhjfhfjhfgfghffj组织机构代码）", textFont));
//        cell.setBorderWidthLeft(3);
//        cell.setMinimumHeight(40); //设置单元格高度
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" ", textFont));
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("注册地址", textFont));
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" ", textFont));
//        cell.setBorderWidthRight(3);
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("行业分类", textFont));
//        cell.setBorderWidthLeft(3);
//        cell.setMinimumHeight(30); //设置单元格高度
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" ", textFont));
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("实缴资本", textFont));
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" ", textFont));
//        cell.setBorderWidthRight(3);
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("数据来源", textFont));
//        cell.setBorderWidthLeft(3);
//        cell.setBorderWidthBottom(3);
//        cell.setMinimumHeight(30); //设置单元格高度
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" ", textFont));
//        cell.setBorderWidthBottom(3);
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("采集时间", textFont));
//        cell.setBorderWidthBottom(3);
//        cell.setUseAscender(true); //设置可以居中
//        cell.setHorizontalAlignment(1); //设置水平居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" ", textFont));
//        cell.setBorderWidthRight(3);
//        cell.setBorderWidthBottom(3);
//        cell.setUseAscender(true); //设置可以居中
//        cell.setVerticalAlignment(1); //设置垂直居中
//        table.addCell(cell);
        doc.add(table);
        doc.close();
    }

    public void tableTest(OutputStream outputStream) {
        PdfDiv pdfDiv = new PdfDiv();
        pdfDiv.setBackgroundColor(BaseColor.RED);
        Document document = new Document();
//        PdfDocument pdfDocument = new PdfDocument();
//        PdfWriter.getInstance(pdfDiv,outputStream);

    }

    public String decompling(OutputStream outputStream, JSONArray nodeJsonObj) throws Exception {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String result = nodeJsonObj.toJSONString();
        JSONObject mainJObj = nodeJsonObj.getJSONObject(0);

        return result;
    }
    public Document getJOtoDoc(JSONObject mainObj,Element fatherElement){
        if(mainObj.getBoolean("isLastNode")){

        }else{
//            i
//            JSONArray children = mainObj.getJSONArray("children");
//            Iterator iterator = children.iterator();
//            while (iterator.hasNext()){
//                getJOtoDoc((JSONObject)iterator.next());
//            }
        }
        return null;
    }
    public Element initElement(JSONObject jsonObject){
        String ename = jsonObject.getString("ename");
        switch (ename){
            case "Page":

        }
        return null;
    }

    public Document initDocument(JSONObject jsonObject){

        Rectangle rectangle = new Rectangle(PageSize.("A4"));
        Document document = new Document()
    }


}
