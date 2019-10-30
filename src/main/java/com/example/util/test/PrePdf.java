package com.example.util.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PrePdf {

    public static JSONObject data;
    public static void main(String[] args) throws Exception {
//        createPDF();
        String testJson = "[{\"check_Focus\":false,\"pageSize\":\"A4\",\"marginRight\":\"16\",\"children\":[{\"check_Focus\":false,\"parentTId\":\"treeDemo_1\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":11,\"tId\":\"treeDemo_3\",\"ename\":\"Cell\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"name\":\"单元格\",\"checked\":false,\"isLastNode\":false,\"halfCheck\":false,\"check_Child_State\":0,\"id\":111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"paddingRight\":16,\"cellHeight\":15,\"paddingBottom\":16,\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":112,\"zAsync\":false,\"paddingTop\":16,\"nocheck\":false,\"isFirstNode\":false,\"chkDisabled\":false,\"rowSpan\":1,\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"colSpan\":1,\"checkedOld\":false,\"rotation\":0,\"pId\":11,\"tId\":\"treeDemo_6\",\"ename\":\"Cell\",\"horizontalAlignment\":\"0\",\"name\":\"单元格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"paddingLeft\":16,\"open\":true,\"verticalAlignment\":\"6\"}],\"editNameFlag\":false,\"checked\":false,\"id\":11,\"zAsync\":true,\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":true,\"isHover\":true,\"level\":1,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1,\"colsRadioArr\":[12,23],\"numCols\":2,\"tId\":\"treeDemo_2\",\"ename\":\"Table\",\"widthRadio\":100,\"name\":\"表格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":1,\"zAsync\":true,\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":true,\"isHover\":false,\"level\":0,\"isAjaxing\":false,\"checkedOld\":false,\"colsRadioArr\":[],\"tId\":\"treeDemo_1\",\"marginLeft\":\"16\",\"ename\":\"Page\",\"pageRotate\":\"vertical\",\"name\":\"纸张与文档\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"marginBottom\":\"16\",\"open\":true,\"marginTop\":\"16\"},{\"check_Focus\":false,\"parentTId\":\"treeDemo_1\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":11,\"tId\":\"treeDemo_3\",\"ename\":\"Cell\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"name\":\"单元格\",\"checked\":false,\"isLastNode\":false,\"halfCheck\":false,\"check_Child_State\":0,\"id\":111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"paddingRight\":16,\"cellHeight\":15,\"paddingBottom\":16,\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":112,\"zAsync\":false,\"paddingTop\":16,\"nocheck\":false,\"isFirstNode\":false,\"chkDisabled\":false,\"rowSpan\":1,\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"colSpan\":1,\"checkedOld\":false,\"rotation\":0,\"pId\":11,\"tId\":\"treeDemo_6\",\"ename\":\"Cell\",\"horizontalAlignment\":\"0\",\"name\":\"单元格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"paddingLeft\":16,\"open\":true,\"verticalAlignment\":\"6\"}],\"editNameFlag\":false,\"checked\":false,\"id\":11,\"zAsync\":true,\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":true,\"isHover\":true,\"level\":1,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1,\"colsRadioArr\":[12,23],\"numCols\":2,\"tId\":\"treeDemo_2\",\"ename\":\"Table\",\"widthRadio\":100,\"name\":\"表格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"open\":true},{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":11,\"tId\":\"treeDemo_3\",\"ename\":\"Cell\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"name\":\"单元格\",\"checked\":false,\"isLastNode\":false,\"halfCheck\":false,\"check_Child_State\":0,\"id\":111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false},{\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"paddingRight\":16,\"cellHeight\":15,\"paddingBottom\":16,\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":112,\"zAsync\":false,\"paddingTop\":16,\"nocheck\":false,\"isFirstNode\":false,\"chkDisabled\":false,\"rowSpan\":1,\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"colSpan\":1,\"checkedOld\":false,\"rotation\":0,\"pId\":11,\"tId\":\"treeDemo_6\",\"ename\":\"Cell\",\"horizontalAlignment\":\"0\",\"name\":\"单元格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"paddingLeft\":16,\"open\":true,\"verticalAlignment\":\"6\"},{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}]";
        JSONArray jo = JSONArray.parseArray(testJson);
//        decompling(jo);
//        JSONObject jo1 = jo.getJSONObject(0);
//        JSONObject joc1 = jo1.getJSONArray("children").getJSONObject(0);
//        JSONArray joa = joc1.getJSONArray("colsRadioArr");
////        int[] gogo;
////        List<Integer> list = JSONArray.parseArray(joa,Integer.class);
//        System.out.println(joa.get(0));
//        if(jo.get())
//        System.out.println(jo.toJSONString());
    }

    public void createPDF(OutputStream outputStream) throws Exception {
        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);
        //设置横向
//        rect = rect.rotate();

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
//        Chunk c1 = new Chunk("*韩国计划*", textFont);
//        doc.add(c1);
//
//        //段落
//        Paragraph p1 = new Paragraph();
//        //短语
//        Phrase ph1 = new Phrase();
//        //块
//        Chunk c2 = new Chunk("*********", textFont);
//        Chunk c11 = new Chunk("（信用报告提供机构l ogo）", textFont);
//        Chunk c3 = new Chunk();
//        c3.append("ffffffff");
//        c3.setFont(textFont);
//        //将块添加到短语
//        ph1.add(c2);
//        ph1.add(c11);
//        ph1.add(c3);
//        //将短语添加到段落
//        p1.add(ph1);
//        p1.setSpacingBefore(100);
//        //将段落添加到短语
//        PdfPTable table3 = new PdfPTable(2);
//        table3.setWidthPercentage(100f);
//        PdfPCell celltempr;
//        celltempr = new PdfPCell(p1);
//        table3.addCell(celltempr);
//        table3.completeRow();
//        doc.add(table3);


        // 创建一个有4列的表格
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{105, 170});
        table.setWidthPercentage(100);

        PdfPCell celltemp0;
        Chunk chunk1 =new Chunk("第三方",textFont);
//        Paragraph paragraph = new Paragraph(56, "第三方", textFont);
        Paragraph paragraph = new Paragraph();
        paragraph.add(chunk1);
        celltemp0 = new PdfPCell(paragraph);

//        PdfPCell celltemp44 = new PdfPCell(paragraph);
//        celltemp0.addElement(paragraph);
        celltemp0.setFixedHeight(20);
        celltemp0.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(celltemp0);


        PdfPCell celltemp;
        celltemp = new PdfPCell(new Phrase("企业名称", textFont));
        celltemp.setFixedHeight(20);
//        celltemp.setPadding();
//        Image img  = new Image() {
//        }
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

    public void tableTest(OutputStream outputStream) throws DocumentException, IOException {
        Rectangle rect = new Rectangle(PageSize.A4);

        Document doc = new Document(rect);
        doc.setMargins(16, 16, 16, 16);
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font textFont = new Font(bfChinese, 11, Font.NORMAL);
        //创建输出流
        PdfWriter.getInstance(doc, outputStream);
        doc.open();
        doc.newPage();
        PdfPTable pdfPTable = new PdfPTable(new float[]{3,7});
        pdfPTable.setWidthPercentage(100);
        pdfPTable.getDefaultCell().setBorder(PdfPCell.BOX);

        PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.BOX);
        cell1.setVerticalAlignment(4);
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);//        cell1.setColspan(1);
//        cell1.setRowspan(1);
//        cell1.setRotation(0);
        cell1.setFixedHeight(20);
//        cell1.setPaddingLeft(0);
//        cell1.setPaddingRight(0);
//        cell1.setPaddingTop(0);
//        cell1.setPaddingBottom(0);

        Paragraph paragraph1 = new Paragraph();
        Chunk chunk1 = new Chunk("ahfff",textFont);
        paragraph1.add(chunk1);
        cell1.addElement(paragraph1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.BOX);
        cell2.setVerticalAlignment(6);
        cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//        cell2.setColspan(1);
//        cell2.setRowspan(1);
//        cell2.setRotation(0);
        cell2.setFixedHeight(20);
//        cell2.setPaddingLeft(0);
//        cell2.setPaddingRight(0);
//        cell2.setPaddingTop(0);
//        cell2.setPaddingBottom(0);

        Paragraph paragraph2 = new Paragraph();
        Chunk chunk2 = new Chunk("sdadsa",textFont);
        paragraph2.add(chunk2);
        cell2.addElement(paragraph2);

        pdfPTable.addCell(cell1);
        pdfPTable.addCell(cell2);
        doc.add(pdfPTable);
        doc.close();
    }

    /**
     * @return java.lang.String
     * @throws
     * @description 解析器入口
     * @author lx
     * @params [outputStream, nodeJsonObj]
     * @date 2019-08-01 20:36
     */
    public  String decompling( JSONObject nodeJsonObj) throws Exception {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() ;
        String parentPath = "/static/templates";
//        String path2 = request.getSession().getServletContext().getContextPath() + parentPath;
//        String result = nodeJsonObj.toJSONString();
//        System.out.println(result);
        JSONObject docJson = nodeJsonObj;
        JSONArray mainArr = docJson.getJSONArray("children");
        Document document = initDocument(docJson);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, os);
        document.open();
        document.newPage();

        ArrayList<Element> elements = countChange(mainArr);
        for (Element element : elements) {
            document.add(element);
        }
        document.close();

        String timestamp = System.currentTimeMillis()+"";
        String tempFilePath = "/gogo"+timestamp+".pdf";
        File pdfFile = new File(path + parentPath+tempFilePath);
        if (pdfFile.exists()) {
            pdfFile.delete();
        }
        byte bWrite[] = os.toByteArray();
        OutputStream fileOs = new FileOutputStream(pdfFile);
        for (int x = 0; x < bWrite.length; x++) {
            fileOs.write(bWrite[x]); // writes the bytes
        }
        fileOs.close();
        os.close();
        return parentPath+tempFilePath;

    }

    /**
     * @description 初始化参数
     * @author      刘鑫（1661）
     * @return      void
     * @Params      []
     * @date        2019/10/29 21:46
     */
    public static void initData(){
        JSONObject dataMap = new JSONObject();
        dataMap.put("param1","参数1");
        dataMap.put("param2","参数2");
        dataMap.put("xxmc","某某学校");
        data = dataMap;
    }

    /**
     * @description 开始解析
     * @author      刘鑫（1661）
     * @return      java.util.ArrayList<com.itextpdf.text.Element>
     * @Params      [mainArr]
     * @date        2019/10/22 22:03
     */
    public ArrayList<Element> countChange(JSONArray mainArr) throws IOException, DocumentException {
        initData();
        ArrayList<Element> docElements;
        docElements = parseChildren(mainArr);
        return docElements;
//        document.close();
    }

    /**
     * @return java.util.ArrayList<com.itextpdf.text.Element>
     * @throws
     * @description 解析子节点们
     * @author lx
     * @params [jsonArray]
     * @date 2019-08-01 20:28
     */
    public ArrayList<Element> parseChildren(JSONArray jsonArray) throws IOException, DocumentException {

        ArrayList<Element> elements = new ArrayList<>();
        Element element;
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            element = initElement((JSONObject) iterator.next());
            elements.add(element);
        }
        return elements;
    }

    public Document getJOtoDoc(JSONObject mainObj, Element fatherElement) throws IOException, DocumentException {
        if (mainObj.getBoolean("isLastNode")) {

        } else {
            Element e = initElement(mainObj);

//            i
//            JSONArray children = mainObj.getJSONArray("children");
//            Iterator iterator = children.iterator();
//            while (iterator.hasNext()){
//                getJOtoDoc((JSONObject)iterator.next());
//            }
        }
        return null;
    }

    /**
     * @return com.itextpdf.text.Element
     * @throws
     * @description 初始化元素
     * @author lx
     * @params [jsonObject]
     * @date 2019-07-31 21:34
     */
    public Element initElement(JSONObject jsonObject) throws IOException, DocumentException {
        String ename = jsonObject.getString("ename");
        Element result;
        switch (ename) {
            case "Table":
                result = initTable(jsonObject);
                break;
            case "Cell":
                result = initCell(jsonObject);
                break;
            case "Paragraph":
                result = initParagraph(jsonObject);
                break;
            case "Chunk":
                result = initChunk(jsonObject);
                break;
            default:
                result = null;

        }
        return result;
    }

    /**
     * @return com.itextpdf.text.Chunk
     * @throws
     * @description 初始化块--最小文本单元
     * @author lx
     * @params [jsonObject]
     * @date 2019-08-01 20:28
     */
    public Chunk initChunk(JSONObject jsonObject) throws IOException, DocumentException {

        String text = jsonObject.getString("text");
        String parameter = jsonObject.getString("parameter");
        String fontBase = jsonObject.getString("fontBase");
        String resultData = "";
        int fontSize = jsonObject.getIntValue("fontSize");
        int fontStyle = jsonObject.getIntValue("fontStyle");
        fontSize = fontSize == 0 ? 9 : fontSize;
        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //设置字体样式
        Font textFont = new Font(bfChinese, fontSize, fontStyle); //正常
        Chunk result = new Chunk();
        resultData = text;
        List<String> keyList = getKeyList(parameter);

        if(parameter!=null && parameter.length()>0 && keyList.size() > 0){
            for(String key : keyList){
                parameter = parameter.replace("{"+key+"}", StringUtils.defaultIfEmpty(data.getString(key),""));
            }
            resultData = parameter;
        }
        result.append(resultData);
        result.setFont(textFont);
        return result;


    }

    /**
     * @return com.itextpdf.text.Paragraph
     * @throws
     * @description 初始化段落
     * @author lx
     * @params [jsonObject]
     * @date 2019-08-01 19:12
     */
    public Paragraph initParagraph(JSONObject jsonObject) throws IOException, DocumentException {
        Paragraph result = new Paragraph();
        ArrayList<Element> elements = parseChildren(jsonObject.getJSONArray("children"));
        for (Element element : elements) {
            result.add(element);
        }
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @throws
     * @description 初始化Table元素
     * @author lx
     * @params [jsonObject]
     * @date 2019-07-31 21:28
     */
    public PdfPTable initTable(JSONObject jsonObject) throws IOException, DocumentException {
        int numCols = jsonObject.getIntValue("numCols");
        float[] colsArr = new float[numCols];
        JSONArray colsRadioArr = jsonObject.getJSONArray("colsRadioArr");
        int colsRadioArrSize = colsRadioArr.size();
        for (int x = 0; x < colsRadioArrSize; x++) {
            colsArr[x] = colsRadioArr.getFloatValue(x);
        }
        PdfPTable result = new PdfPTable(colsArr);
        ArrayList<Element> elements = parseChildren(jsonObject.getJSONArray("children"));
        for (Element element : elements) {
//            result.addCell(element);
            if (element instanceof PdfPCell) {
                result.addCell((PdfPCell) element);
            } else if (element instanceof PdfPTable) {
                result.addCell((PdfPTable) element);
            }
        }
        result.getDefaultCell().setBorder(PdfPCell.BOX);
        result.completeRow();
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPCell
     * @throws
     * @description 初始化单元格
     * @author lx
     * @params [jsonObject]
     * @date 2019-07-31 21:50
     */
    public PdfPCell initCell(JSONObject jsonObject) throws IOException, DocumentException {
        int colSpan = jsonObject.getIntValue("colSpan");
        int rowSpan = jsonObject.getIntValue("rowSpan");
        int rotation = jsonObject.getIntValue("rotation");
        int cellHeight = jsonObject.getIntValue("cellHeight");
        int paddingLeft = jsonObject.getIntValue("paddingLeft");
        int paddingRight = jsonObject.getIntValue("paddingRight");
        int paddingTop = jsonObject.getIntValue("paddingTop");
        int paddingBottom = jsonObject.getIntValue("paddingBottom");
        int horizontalAlignment = jsonObject.getIntValue("horizontalAlignment");
        int verticalAlignment = jsonObject.getIntValue("verticalAlignment");
        float borderWidthLeft = jsonObject.getFloatValue("borderWidthLeft");
        float borderWidthRight = jsonObject.getFloatValue("borderWidthRight");
        float borderWidthTop = jsonObject.getFloatValue("borderWidthTop");
        float borderWidthBottom = jsonObject.getFloatValue("borderWidthBottom");
        JSONArray paragraphJsonObjArr = jsonObject.getJSONArray("children");
        JSONObject pgphJsonObj = paragraphJsonObjArr.getJSONObject(0);
        Paragraph paragraph = initParagraph(pgphJsonObj);
        PdfPCell pdfPCell = new PdfPCell(paragraph);
        pdfPCell.setColspan(colSpan);
        pdfPCell.setRowspan(rowSpan);
        pdfPCell.setRotation(rotation);
        pdfPCell.setFixedHeight(cellHeight);
        pdfPCell.setPaddingLeft(paddingLeft);
        pdfPCell.setPaddingRight(paddingRight);
        pdfPCell.setPaddingTop(paddingTop);
        pdfPCell.setPaddingBottom(paddingBottom);
        pdfPCell.setHorizontalAlignment(horizontalAlignment);
        pdfPCell.setVerticalAlignment(verticalAlignment);
        pdfPCell.setBorderWidthLeft(borderWidthLeft);
        pdfPCell.setBorderWidthRight(borderWidthRight);
        pdfPCell.setBorderWidthTop(borderWidthTop);
        pdfPCell.setBorderWidthBottom(borderWidthBottom);
        pdfPCell.setBorder(PdfPCell.BOX);
//        ArrayList<Element> elements = parseChildren(jsonObject.getJSONArray("children"));
//        pdfPCell.addElement(elements.get(0));
        return pdfPCell;
    }

    /**
     * @return com.itextpdf.text.Document
     * @throws
     * @description 初始化文档元素
     * @author lx
     * @params [jsonObject]
     * @date 2019-07-31 21:29
     */
    public Document initDocument(JSONObject jsonObject) {
        String pageSize = jsonObject.getString("pageSize");
        String pageRotate = jsonObject.getString("pageRotate");
        Rectangle rectangle = getPageSizeByName(pageSize);
        Document document;
        float marginLeft = jsonObject.getFloat("marginLeft");
        float marginRight = jsonObject.getFloat("marginRight");
        float marginTop = jsonObject.getFloat("marginTop");
        float marginBottom = jsonObject.getFloat("marginBottom");
        if ("horizontal".equals(pageRotate)) {
            rectangle = rectangle.rotate();
        }
        document = new Document(rectangle);
        document.setMargins(marginLeft, marginRight, marginTop, marginBottom);
        return document;
    }

    public Rectangle getPageSizeByName(String pageSizeName) {
        Rectangle result;
        switch (pageSizeName) {
            case "A3":
                result = PageSize.A3;
                break;
            default:
                result = PageSize.A4;
        }
        return result;
    }

    /**
     * @description 获取chunk的参数表达式提取map的key
     * @author      刘鑫（1661）
     * @return      java.util.List<java.lang.String>
     * @Params      [input]
     * @date        2019/10/30 21:27
     */
    public static java.util.List<String> getKeyList (String input ){
        if(input== null || input.length()<1){
            return null;
        }
        List<String> result = new ArrayList<>();
        char[] inputCharArr = input.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        boolean openFlag = false;
        for(int x = 0; x<inputCharArr.length;x++){
            if('{' == inputCharArr[x]){
                startIndex = x;
                openFlag = true;
            }else if(openFlag && '}' == inputCharArr[x]){
                endIndex = x;
                openFlag = false;
                result.add(input.substring(startIndex+1,endIndex));
            }
        }
        return result;
    }

}
