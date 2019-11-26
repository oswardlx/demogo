package com.example.util.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PrePdf {

    public static JSONObject data;
    public static JSONObject properties;
    public static BaseFont BFCHINESE;

    static {
        try {
            BFCHINESE = getLocalFont("Alibaba-PuHuiTi-Regular.otf");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public PrePdf() throws IOException, DocumentException {
    }

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
//        doc.set
        doc.setMargins(12, 12, 50, 12);

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //设置字体样式
        Font textFont = new Font(bfChinese, 11, Font.NORMAL); //正常


        //创建输出流
        PdfWriter writer = PdfWriter.getInstance(doc, outputStream);

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
        PdfPTable pdfPTable = new PdfPTable(new float[]{3, 7});
        pdfPTable.setWidthPercentage(100);
        pdfPTable.getDefaultCell().setBorder(PdfPCell.BOX);
        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.BOX);
        cell2.setVerticalAlignment(6);
        cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

        // 创建一个有4列的表格
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{105, 170});
        table.setWidthPercentage(100);
        PdfPCell celltemp0;
        Chunk chunk1 = new Chunk("head", textFont);
        Paragraph paragraph = new Paragraph();
        paragraph.add(chunk1);
        celltemp0 = new PdfPCell(paragraph);
        celltemp0.setFixedHeight(20);
        celltemp0.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(celltemp0);
        PdfPCell celltemp;
        celltemp = new PdfPCell(new Phrase("head1", textFont));
        celltemp.setFixedHeight(20);
        table.addCell(celltemp);

        celltemp = new PdfPCell(new Phrase("fotter", textFont));
        celltemp.setFixedHeight(20);
        table.addCell(celltemp);
        celltemp = new PdfPCell(new Phrase("fotter", textFont));
        celltemp.setFixedHeight(20);
        table.addCell(celltemp);
        for(int x = 0 ;x <100 ; x++) {
            chunk1 = new Chunk("第三方", textFont);
            paragraph = new Paragraph();
            paragraph.add(chunk1);
            celltemp0 = new PdfPCell(paragraph);
            celltemp0.setFixedHeight(20);
            celltemp0.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(celltemp0);
            celltemp = new PdfPCell(new Phrase("企业名称", textFont));
            celltemp.setFixedHeight(20);
            table.addCell(celltemp);
        }

        table.completeRow();
        table.setHeaderRows(0);
        table.setFooterRows(2);

        Paragraph paragraph2 = new Paragraph();
        Chunk chunk2 = new Chunk("sdadsa", textFont);
        paragraph2.add(chunk2);
        cell2.addElement(paragraph2);
        pdfPTable.addCell(cell2);
        pdfPTable.addCell(cell2);
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
        doc.add(pdfPTable);
        doc.close();
    }

    public void tableTest(OutputStream outputStream) throws DocumentException, IOException {
        long startTime = System.currentTimeMillis();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String parentPath = "/static/templates";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Rectangle rect = new Rectangle(PageSize.A4);
        Document doc = new Document(rect);
        doc.setMargins(16, 16, 16, 16);
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font textFont = new Font(bfChinese, 11, Font.NORMAL);
        //创建输出流
        PdfWriter.getInstance(doc, outputStream);
        doc.open();
        doc.newPage();
        PdfPTable pdfPTable = new PdfPTable(new float[]{3, 7});
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
        Chunk chunk1 = new Chunk("ahfff", textFont);
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
        Chunk chunk2 = new Chunk("sdadsa", textFont);
        paragraph2.add(chunk2);
        cell2.addElement(paragraph2);

        pdfPTable.addCell(cell1);
        pdfPTable.addCell(cell2);
        doc.add(pdfPTable);
        doc.close();

        String timestamp = System.currentTimeMillis() + "";
        String tempFilePath = "/gogo" + timestamp + ".pdf";
        File pdfFile = new File(path + parentPath + tempFilePath);
        if (pdfFile.exists()) {
            pdfFile.delete();
        }
        byte bWrite[] = os.toByteArray();
        OutputStream fileOs = new FileOutputStream(pdfFile);
        int size = 1024 * 100;
        byte[] temp = null;
        for (int x = 0; x < bWrite.length; x += size) {
            if (x + size > bWrite.length) {
                temp = Arrays.copyOfRange(bWrite, x, bWrite.length);
                fileOs.write(temp);
                break;
            }
            temp = Arrays.copyOfRange(bWrite, x, x + size);
            fileOs.write(temp);
        }
        fileOs.close();


//        executorDeleteFile(path + parentPath + tempFilePath, 10, 2000);
//        return parentPath + tempFilePath;

        byte bWrite2 [] = os.toByteArray();
        PdfReader reader = new PdfReader(bWrite2);
//        writer.setEncryption("Hello".getBytes(), "World".getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.STANDARD_ENCRYPTION_128);
        PdfStamper stamper = PdfStamper.createSignature(reader, outputStream, '\0', null, true);
        // 获取数字签章属性对象，设定数字签章的属性
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason("写个原因");
        appearance.setLocation("写个位置");
        //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
        //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        appearance.setVisibleSignature(new Rectangle(200, 200, 300, 300), 1, "sig1");
        //读取图章图片，这个image是itext包的image
        Image image = Image.getInstance("F:\\prosssss\\demogogogogog\\src\\main\\resources\\static\\pic\\fff.png");
        appearance.setSignatureGraphic(image);
        appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
        //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
        os.close();
    }


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @description 后台模拟循环体参数
     * @author 刘鑫（1661）
     * @Params []
     * @date 2019/11/13 22:53
     */
    public JSONObject getLoopProperties() {
        JSONObject tempJO;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("repeat", 3);
        jsonObject.put("rows", 24);
        jsonObject.put("resultWidthRadio", 100f);
        jsonObject.put("hasHead", true);
        jsonObject.put("cols", 6);
        jsonObject.put("fontSize", 8);
        jsonObject.put("cellHeight", 13
        );
        JSONArray colsRadioArr = new JSONArray();
        colsRadioArr.add(40);
        colsRadioArr.add(10);
        colsRadioArr.add(10);
        colsRadioArr.add(10);
        colsRadioArr.add(10);
        colsRadioArr.add(10);
        jsonObject.put("colsRadioArr", colsRadioArr);
        jsonObject.put("repeatWidthRadio", 100f);
        jsonObject.put("head1", "课   程\n名  称");
        jsonObject.put("head2", "课程\n性质");
        jsonObject.put("head3", "学\n分");
        jsonObject.put("head4", "成\n绩");
        jsonObject.put("head5", "补\n考");
        jsonObject.put("head6", "重\n修");
        jsonObject.put("headFontSize", "10");
        jsonObject.put("headFontStyle", "10");
        jsonObject.put("borderWidthLeft", "0.5");
        jsonObject.put("borderWidthRight", "0.5");
        jsonObject.put("borderWidthTop", "0.5");
        jsonObject.put("borderWidthBottom", "0.5");
        //1
        JSONArray cellPropArr = new JSONArray();
        tempJO = new JSONObject();
        tempJO.put("colSpan", 1);
        tempJO.put("rowSpan", 1);
        tempJO.put("rotation", 0);
        tempJO.put("cellHeight", 13);
        tempJO.put("paddingLeft", 4);
        tempJO.put("paddingRight", 0);
        tempJO.put("paddingTop", 0);
        tempJO.put("paddingBottom", 0);
        tempJO.put("borderWidthBottom", 0.5);
        tempJO.put("borderWidthLeft", 0.5);
        tempJO.put("borderWidthRight", 0.5);
        tempJO.put("borderWidthTop", 0.5);
        tempJO.put("horizontalAlignment", PdfPCell.ALIGN_LEFT);
        tempJO.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
        tempJO.put("fontSize", 8);
        tempJO.put("fontStyle", 0);
        tempJO.put("fontBase", "Alibaba-PuHuiTi-Regular.otf");
        cellPropArr.add(tempJO);
        //2
        tempJO = new JSONObject();
        tempJO.put("colSpan", 1);
        tempJO.put("rowSpan", 1);
        tempJO.put("rotation", 0);
        tempJO.put("cellHeight", 13);
        tempJO.put("paddingLeft", 4);
        tempJO.put("paddingRight", 0);
        tempJO.put("paddingTop", 0);
        tempJO.put("paddingBottom", 0);
        tempJO.put("borderWidthBottom", 0.5);
        tempJO.put("borderWidthLeft", 0.5);
        tempJO.put("borderWidthRight", 0.5);
        tempJO.put("borderWidthTop", 0.5);
        tempJO.put("horizontalAlignment", PdfPCell.ALIGN_CENTER);
        tempJO.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
        tempJO.put("fontSize", 8);
        tempJO.put("fontStyle", 0);
        tempJO.put("fontBase", "Alibaba-PuHuiTi-Regular.otf");
        cellPropArr.add(tempJO);
        //3
        tempJO = new JSONObject();
        tempJO.put("colSpan", 1);
        tempJO.put("rowSpan", 1);
        tempJO.put("rotation", 0);
        tempJO.put("cellHeight", 13);
        tempJO.put("paddingLeft", 4);
        tempJO.put("paddingRight", 0);
        tempJO.put("paddingTop", 0);
        tempJO.put("paddingBottom", 0);
        tempJO.put("borderWidthBottom", 0.5);
        tempJO.put("borderWidthLeft", 0.5);
        tempJO.put("borderWidthRight", 0.5);
        tempJO.put("borderWidthTop", 0.5);
        tempJO.put("horizontalAlignment", PdfPCell.ALIGN_CENTER);
        tempJO.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
        tempJO.put("fontSize", 8);
        tempJO.put("fontStyle", 0);
        tempJO.put("fontBase", "Alibaba-PuHuiTi-Regular.otf");
        cellPropArr.add(tempJO);
        //4
        tempJO = new JSONObject();
        tempJO.put("colSpan", 1);
        tempJO.put("rowSpan", 1);
        tempJO.put("rotation", 0);
        tempJO.put("cellHeight", 13);
        tempJO.put("paddingLeft", 4);
        tempJO.put("paddingRight", 0);
        tempJO.put("paddingTop", 0);
        tempJO.put("paddingBottom", 0);
        tempJO.put("borderWidthBottom", 0.5);
        tempJO.put("borderWidthLeft", 0.5);
        tempJO.put("borderWidthRight", 0.5);
        tempJO.put("borderWidthTop", 0.5);
        tempJO.put("horizontalAlignment", PdfPCell.ALIGN_CENTER);
        tempJO.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
        tempJO.put("fontSize", 8);
        tempJO.put("fontStyle", 0);
        tempJO.put("fontBase", "Alibaba-PuHuiTi-Regular.otf");
        cellPropArr.add(tempJO);
        //5
        tempJO = new JSONObject();
        tempJO.put("colSpan", 1);
        tempJO.put("rowSpan", 1);
        tempJO.put("rotation", 0);
        tempJO.put("cellHeight", 13);
        tempJO.put("paddingLeft", 4);
        tempJO.put("paddingRight", 0);
        tempJO.put("paddingTop", 0);
        tempJO.put("paddingBottom", 0);
        tempJO.put("borderWidthBottom", 0.5);
        tempJO.put("borderWidthLeft", 0.5);
        tempJO.put("borderWidthRight", 0.5);
        tempJO.put("borderWidthTop", 0.5);
        tempJO.put("horizontalAlignment", PdfPCell.ALIGN_CENTER);
        tempJO.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
        tempJO.put("fontSize", 8);
        tempJO.put("fontStyle", 0);
        tempJO.put("fontBase", "Alibaba-PuHuiTi-Regular.otf");
        cellPropArr.add(tempJO);
        //6
        tempJO = new JSONObject();
        tempJO.put("colSpan", 1);
        tempJO.put("rowSpan", 1);
        tempJO.put("rotation", 0);
        tempJO.put("cellHeight", 13);
        tempJO.put("paddingLeft", 4);
        tempJO.put("paddingRight", 0);
        tempJO.put("paddingTop", 0);
        tempJO.put("paddingBottom", 0);
        tempJO.put("borderWidthBottom", 0.5);
        tempJO.put("borderWidthLeft", 0.5);
        tempJO.put("borderWidthRight", 0.5);
        tempJO.put("borderWidthTop", 0.5);
        tempJO.put("horizontalAlignment", PdfPCell.ALIGN_CENTER);
        tempJO.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
        tempJO.put("fontSize", 8);
        tempJO.put("fontStyle", 0);
        tempJO.put("fontBase", "Alibaba-PuHuiTi-Regular.otf");
        cellPropArr.add(tempJO);

//        JSONArray colProperties = new JSONArray();
//        JSONObject col1 = new JSONObject();
//        col1.put("")
        jsonObject.put("cellPropArr",cellPropArr);
        return jsonObject;
    }


    /**
     * @return java.lang.String
     * @throws
     * @description 解析器入口
     * @author lx
     * @params [outputStream, nodeJsonObj]
     * @date 2019-08-01 20:36
     */
    public String decompling(JSONObject nodeJsonObj) throws Exception {
        initData();
        long startTime = System.currentTimeMillis();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String parentPath = "/static/templates";
        //初始化document
        JSONObject docJson = nodeJsonObj;
        JSONArray mainArr = docJson.getJSONArray("children");
        Document document = initDocument(docJson);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, os);
        document.open();
        long endTime = System.currentTimeMillis();
        System.out.println("\ngetPath cost: " + Math.abs(endTime - startTime) + " (ms)");
        PdfPTable cPageNum =  initLoop();
        PdfPTable mountTable = (PdfPTable)(cPageNum.getRow(0).getCells()[0].getColumn().getCompositeElements().get(0));
        int rows = mountTable.getRows().size();
        int cjJAMount = rows;
        int pageCount = cjJAMount / (properties.getIntValue("rows") * properties.getIntValue("repeat"));
        int pageResidue = cjJAMount % (properties.getIntValue("rows") * properties.getIntValue("repeat"));
        int pageNum = pageResidue > 0 ? pageCount + 1 : (pageCount > 0 ? pageCount : 1);
        for (int x = 0; x < pageNum; x++) {
            document.newPage();
            ArrayList<Element> elements = countChange(mainArr);
            for (Element element : elements) {
                document.add(element);
            }
        }
        document.close();
        startTime = System.currentTimeMillis();
        System.out.println("countChange cost: " + Math.abs(endTime - startTime) + " (ms)");
        String timestamp = System.currentTimeMillis() + "";
        String tempFilePath = "/gogo" + timestamp + ".pdf";
        File pdfFile = new File(path + parentPath + tempFilePath);
        if (pdfFile.exists()) {
            pdfFile.delete();
        }
        byte bWrite[] = os.toByteArray();
        OutputStream fileOs = new FileOutputStream(pdfFile);
        int size = 1024 * 100;
        byte[] temp = null;
        for (int x = 0; x < bWrite.length; x += size) {
            if (x + size > bWrite.length) {
                temp = Arrays.copyOfRange(bWrite, x, bWrite.length);
                fileOs.write(temp);
                break;
            }
            temp = Arrays.copyOfRange(bWrite, x, x + size);
            fileOs.write(temp);
        }
        fileOs.close();
        os.close();
        endTime = System.currentTimeMillis();
        System.out.println("write cost: " + Math.abs(endTime - startTime) + " (ms)");
        executorDeleteFile(path + parentPath + tempFilePath, 10, 2000);
        return parentPath + tempFilePath;
    }

    /**
     * @return void
     * @description 初始化参数
     * @author 刘鑫（1661）
     * @Params []
     * @date 2019/10/29 21:46
     */
    public JSONObject initData() {
        JSONObject dataMap = new JSONObject();
        dataMap.put("param1", "参数1");
        dataMap.put("param2", "参数2");
        dataMap.put("xxmc", "正方软件");
        dataMap.put("xy", "党委办公室");
        dataMap.put("zy", "给排水工程");
        dataMap.put("bjmc", "给排水1201");
        dataMap.put("xh", "1213300103");
        dataMap.put("xm", "王婷婷");
        dataMap.put("xw", "理学");
        dataMap.put("byzsh", "12345678910");
        dataMap.put("byjl", "结业");
        dataMap.put("bylwtm", "基于测试分析报告的设计方案与维护论文题目a");
        dataMap.put("byyqdzxf", "130");
        dataMap.put("yhdzxf", "126.0");
        dataMap.put("bxkxf", "83.0");
        dataMap.put("xxkxf", "29.0");
        dataMap.put("gxkxf", "13.0");
        dataMap.put("cskxf", "0.0");
        dataMap.put("sjkxf", "1.0");
        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
        String dyrq = sf.format(new Date());
        dataMap.put("dyrq", dyrq);
        JSONArray cjJA = initArray();
        dataMap.put("cjJA", cjJA);
        dataMap.put("loopProperties",getLoopProperties());
        data = dataMap;
        properties = getLoopProperties();
        return dataMap;
    }

    public JSONArray initArray() {
        JSONArray dataJA = new JSONArray();
        JSONObject dataTemp;
        for (int x = 0; x < 8; x++) {
            dataTemp = new JSONObject();
            dataTemp.put("text", "2012-2013学年 第"+x+"学期");
            dataTemp.put("colSpan", 6);
            dataTemp.put("rowSpan", 1);
            dataTemp.put("horizontalAlignment", PdfPCell.ALIGN_CENTER);
            dataTemp.put("verticalAlignment", PdfPCell.ALIGN_MIDDLE);
            dataJA.add(dataTemp);
            for (int y = 0; y < 6; y++) {
                dataTemp = new JSONObject();
                dataTemp.put("text", "课程名称" + x+""+y);
                dataTemp.put("colSpan", 1);
                dataTemp.put("rowSpan", 1);
                dataJA.add(dataTemp);
                dataTemp = new JSONObject();
                dataTemp.put("text", "性质" +  x+""+y);
                dataTemp.put("colSpan", 1);
                dataTemp.put("rowSpan", 1);
                dataJA.add(dataTemp);
                dataTemp = new JSONObject();
                dataTemp.put("text", "学分" + x+""+y);
                dataTemp.put("colSpan", 1);
                dataTemp.put("rowSpan", 1);
                dataJA.add(dataTemp);
                dataTemp = new JSONObject();
                dataTemp.put("text", "成绩" + x+""+y);
                dataTemp.put("colSpan", 1);
                dataTemp.put("rowSpan", 1);
                dataJA.add(dataTemp);
                dataTemp = new JSONObject();
                dataTemp.put("text", "补考" + x+""+y);
                dataTemp.put("colSpan", 1);
                dataTemp.put("rowSpan", 1);
                dataJA.add(dataTemp);
                dataTemp = new JSONObject();
                dataTemp.put("text", "重修" + x+""+y);
                dataTemp.put("colSpan", 1);
                dataTemp.put("rowSpan", 1);
                dataJA.add(dataTemp);
            }
        }
        return dataJA;
    }

    /**
     * @return java.util.ArrayList<com.itextpdf.text.Element>
     * @description 开始解析
     * @author 刘鑫（1661）
     * @Params [mainArr]
     * @date 2019/10/22 22:03
     */
    public ArrayList<Element> countChange(JSONArray mainArr) throws IOException, DocumentException {

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
     * @return java.util.List<com.itextpdf.text.pdf.PdfPCell>
     * @description
     * @author 刘鑫（1661）
     * @Params [dataJA]
     * @date 2019/11/14 20:14
     */
    public List<PdfPCell> transDataToList(JSONArray dataJA, JSONObject propertiesJO) throws IOException, DocumentException {
        List<PdfPCell> result = new ArrayList<>();
        JSONObject tempJO;
        JSONObject tempJO2;
        PdfPCell tempCell;
        Chunk tempChunk;
        Paragraph tempParagraph;
        int cols = propertiesJO.getIntValue("cols");
        JSONArray cellPropArr = propertiesJO.getJSONArray("cellPropArr");
        //用于区分规则与不规则的单元格
        int colIndex = 0;
        for (int x = 0; x < dataJA.size(); x++) {
            tempJO = dataJA.getJSONObject(x);
            tempJO2 = cellPropArr.getJSONObject(colIndex%cols);
            if(1==tempJO.getIntValue("colSpan")){
                colIndex++;
            }
            tempCell = getDataToCell(tempJO,tempJO2);
            result.add(tempCell);
        }
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPCell
     * @description 将数据转获取cell
     * @author 刘鑫（1661）
     * @Params [dataJO, propertiesJO]
     * @date 2019/11/14 20:54
     */
    public PdfPCell getDataToCell(JSONObject dataJO, JSONObject propertiesJO) throws IOException, DocumentException {
        int colSpan = dataJO.getIntValue("colSpan");
        int rowSpan = dataJO.getIntValue("rowSpan");
        int rotation = propertiesJO.getIntValue("rotation");
        int cellHeight = propertiesJO.getIntValue("cellHeight");
        int paddingLeft = propertiesJO.getIntValue("paddingLeft");
        int paddingRight = propertiesJO.getIntValue("paddingRight");
        int paddingTop = propertiesJO.getIntValue("paddingTop");
        int paddingBottom = propertiesJO.getIntValue("paddingBottom");
        String hAlignmentStr = StringUtils.defaultIfEmpty(dataJO.getString("horizontalAlignment"),"");
        String vAlignmentStr = StringUtils.defaultIfEmpty(dataJO.getString("verticalAlignment"),"");
        int horizontalAlignment = hAlignmentStr.length()>0?Integer.parseInt(hAlignmentStr):propertiesJO.getIntValue("horizontalAlignment");
        int verticalAlignment = hAlignmentStr.length()>0?Integer.parseInt(vAlignmentStr):propertiesJO.getIntValue("verticalAlignment");
        float borderWidthLeft = propertiesJO.getFloatValue("borderWidthLeft");
        float borderWidthRight = propertiesJO.getFloatValue("borderWidthRight");
        float borderWidthTop = propertiesJO.getFloatValue("borderWidthTop");
        float borderWidthBottom = propertiesJO.getFloatValue("borderWidthBottom");
        String fontBase = propertiesJO.getString("fontBase");
        int fontSize = propertiesJO.getIntValue("fontSize");
        int fontStyle = propertiesJO.getIntValue("fontStyle");
        String text = dataJO.getString("text");
        fontBase = StringUtils.defaultIfEmpty(fontBase, "Alibaba-PuHuiTi-Regular.otf");
        BaseFont bfChinese = BFCHINESE;
        //设置字体样式
        Font textFont = new Font(bfChinese, fontSize, fontStyle);
        Chunk chunk = new Chunk();
        chunk.append(StringUtils.defaultIfEmpty(text, ""));
        chunk.setFont(textFont);
        Paragraph paragraph = new Paragraph();
        paragraph.add(chunk);
        PdfPCell pdfPCell = new PdfPCell(paragraph);
        pdfPCell.setColspan(colSpan == 0 ? 1 : colSpan);
        pdfPCell.setRowspan(rowSpan == 0 ? 1 : rowSpan);
        pdfPCell.setRotation(rotation);
        if (cellHeight > 0) {
            pdfPCell.setFixedHeight(cellHeight);
        }
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
        return pdfPCell;
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
            case "Loop":
                result = initLoop();
                break;
            default:
                result = null;

        }
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 初始化循环体
     * @author 刘鑫（1661）
     * @Params [jsonObject]
     * @date 2019/11/13 19:49
     */
    public PdfPTable initLoop() throws IOException, DocumentException {
        JSONObject loopPorperties = getLoopProperties();
        JSONArray loopData = initArray();
        PdfPCell tempCell;
        int repeat = loopPorperties.getIntValue("repeat");
        PdfPTable tempTable;
        //构造分列Table参数
        JSONArray colsRadioArr = loopPorperties.getJSONArray("colsRadioArr");
        int cols = loopPorperties.getIntValue("cols");
        float repeatWidthRadio = loopPorperties.getFloatValue("repeatWidthRadio");
        float[] colsArr = new float[cols];
        int colsRadioArrSize = colsRadioArr.size();
        for (int x = 0; x < colsRadioArrSize; x++) {
            colsArr[x] = colsRadioArr.getFloatValue(x);
        }
        PdfPTable[] colTables = new PdfPTable[repeat];
        //构造结果Table
        float resultWidthRadio = loopPorperties.getFloatValue("resultWidthRadio");
        float[] resultArr = new float[repeat];

        for (int x = 0; x < repeat; x++) {
            resultArr[x] = 1;
            //初始化分列Table
            tempTable = new PdfPTable(colsArr);
            tempTable.setWidthPercentage(repeatWidthRadio);
            colTables[x] = tempTable;
        }
        PdfPTable result = new PdfPTable(resultArr);
        result.setWidthPercentage(resultWidthRadio);
        //将数据写入
        JSONArray cjJA = initArray();
        JSONObject loopProperties = getLoopProperties();
        List<PdfPCell> list = transDataToList(cjJA,loopProperties);
        int fromNum = 0;
        //写入数据格子
        for (PdfPTable pt : colTables) {
            for (int x = fromNum; x < list.size(); x++) {
                if (pt.getRows().size() >= 0 && pt.getRows().size() < loopProperties.getIntValue("rows")) {
                    pt.addCell(list.get(x));
                    continue;
                }
                fromNum = x;
                break;
            }
            //补空格
            if(pt.getRows().size() < loopProperties.getIntValue("rows")){
                int append = loopProperties.getIntValue("rows") - pt.getRows().size();
                tempCell = new PdfPCell();
                tempCell.setFixedHeight(loopPorperties.getIntValue("cellHeight"));
                for (int y = 0; y < append*cols; y++) {
                    pt.addCell(tempCell);
                }
            }
            pt.completeRow();
        }
        //todo 将分列table合入结果Talbe
        for (int x = 0; x < colTables.length; x++) {
            tempCell = new PdfPCell(colTables[x]);
//            tempCell.setFixedHeight(100);
            result.addCell(tempCell);
        }
        result.setSpacingBefore(0);
        result.getDefaultCell().setBorder(PdfPCell.BOX);
        result.completeRow();
        return result;


//        ArrayList<Element> elements = parseChildren(jsonObject.getJSONArray("children"));
//        for (Element element : elements) {
////            result.addCell(element);
//            if (element instanceof PdfPCell) {
//                result.addCell((PdfPCell) element);
//            } else if (element instanceof PdfPTable) {
//                result.addCell((PdfPTable) element);
//            }
//        }
//        result.setSpacingBefore(0);
//        result.getDefaultCell().setBorder(PdfPCell.BOX);
//        result.completeRow();
    }


    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 写入数据项+猎头
     * @author 刘鑫（1661）
     * @Params [perPageRowNum, pageColNum, scoreHeight, maxSize, arrays, bfChinese, marginLeftRight, rectangle, allXscjCellList, cellNummap, dataTable, dataArr, pdfPCellArr, height]
     * @date 2019/10/25 11:39
     */
    public PdfPTable writeDataShoot(List<PdfPCell> pdfPCellxlts,int perPageRowNum, int pageColNum, int scoreHeight, List<PdfPCell> allXscjCellList, HashMap<String, Integer> cellNummap, PdfPTable dataTable, PdfPTable[] dataArr, PdfPCell[] pdfPCellArr) throws IOException, DocumentException {
        //定位
        int fromNum = cellNummap.get("fromNum");
        //写入列头
        for (PdfPCell pCellx : pdfPCellxlts) {
            for (PdfPTable pt : dataArr) {
                pt.addCell(pCellx);
            }
        }
        //写入数据格子
        for (PdfPTable pt : dataArr) {
            for (int x = fromNum; x < allXscjCellList.size(); x++) {
                if (pt.getRows().size() >= 0 && pt.getRows().size() < perPageRowNum) {
                    pt.addCell(allXscjCellList.get(x));
                    continue;
                }
                fromNum = x;
                break;
            }
        }
        //table写入布局
        for (int x = 0; x < pageColNum; x++) {
            pdfPCellArr[x].addElement(dataArr[x]);
            dataTable.addCell(pdfPCellArr[x]);
        }
        cellNummap.put("fromNum", fromNum);
        return dataTable;
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
        //添加字体
//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        fontBase = StringUtils.defaultIfEmpty(fontBase, "Alibaba-PuHuiTi-Regular.otf");
        BaseFont bfChinese = getLocalFont(fontBase);
        //设置字体样式
        Font textFont = new Font(bfChinese, fontSize, fontStyle); //正常
        Chunk result = new Chunk();
        resultData = text;
        List<String> keyList = getKeyList(parameter);

        if (parameter != null && parameter.length() > 2 && keyList.size() > 0) {
            for (String key : keyList) {
                parameter = parameter.replace("{" + key + "}", StringUtils.defaultIfEmpty(data.getString(key), ""));
            }
            resultData = parameter;
        }
        resultData = escapeStr(resultData);
        result.append(resultData);
        result.setFont(textFont);
        return result;


    }

    /**
     * @return java.lang.String
     * @description 转义
     * @author 刘鑫（1661）
     * @Params [str]
     * @date 2019/11/9 15:25
     */
    public String escapeStr(String str) {
        String result = StringUtils.defaultIfEmpty(str,"").replace("\\n", "\n");
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
        int widthRadio = jsonObject.getIntValue("widthRadio");
        float[] colsArr = new float[numCols];
        JSONArray colsRadioArr = jsonObject.getJSONArray("colsRadioArr");
        int colsRadioArrSize = colsRadioArr.size();
        for (int x = 0; x < colsRadioArrSize; x++) {
            colsArr[x] = colsRadioArr.getFloatValue(x);
        }
        PdfPTable result = new PdfPTable(colsArr);
        result.setWidthPercentage(widthRadio);
        ArrayList<Element> elements = parseChildren(jsonObject.getJSONArray("children"));
        for (Element element : elements) {
//            result.addCell(element);
            if (element instanceof PdfPCell) {
                result.addCell((PdfPCell) element);
            } else if (element instanceof PdfPTable) {
                result.addCell((PdfPTable) element);
            }
        }
        result.setSpacingBefore(0);
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
        JSONArray childrenObjArr = jsonObject.getJSONArray("children");
        JSONObject childrenObj = childrenObjArr.getJSONObject(0);
        Element childrenElement = initElement(childrenObj);
        PdfPCell pdfPCell = null;
        pdfPCell = getPCell(childrenElement);
        pdfPCell.setColspan(colSpan);
        pdfPCell.setRowspan(rowSpan);
        pdfPCell.setRotation(rotation);
        if (cellHeight > 0) {
            pdfPCell.setFixedHeight(cellHeight);
        }
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
        return pdfPCell;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPCell
     * @description 根据不同类型的元素返回单元格对象，是因为如果用
     * com.itextpdf.text.pdf.PdfPCell#addElement(com.itextpdf.text.Element)这个方法，添加段落元素时单元格左右居中不起作用。
     * @author 刘鑫（1661）
     * @Params [element]
     * @date 2019/11/8 21:14
     */
    public PdfPCell getPCell(Element element) {
        PdfPCell result = null;
        if (element instanceof PdfPTable) {
            result = new PdfPCell((PdfPTable) element);
        } else if (element instanceof PdfPCell) {
            result = new PdfPCell((PdfPCell) element);
        } else if (element instanceof Paragraph) {
            result = new PdfPCell((Paragraph) element);
        } else if (element instanceof Image) {
            result = new PdfPCell((Image) element);
        }
        return result;
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

        document = new Document(rectangle, marginLeft, marginRight, marginTop, marginBottom);
//        document.setMargins(marginLeft, marginRight, marginTop, marginBottom);
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
     * @return java.util.List<java.lang.String>
     * @description 获取chunk的参数表达式提取map的key
     * @author 刘鑫（1661）
     * @Params [input]
     * @date 2019/10/30 21:27
     */
    public java.util.List<String> getKeyList(String input) {
        if (input == null || input.length() < 1) {
            return null;
        }
        List<String> result = new ArrayList<>();
        char[] inputCharArr = input.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        boolean openFlag = false;
        for (int x = 0; x < inputCharArr.length; x++) {
            if ('{' == inputCharArr[x]) {
                startIndex = x;
                openFlag = true;
            } else if (openFlag && '}' == inputCharArr[x]) {
                endIndex = x;
                openFlag = false;
                result.add(input.substring(startIndex + 1, endIndex));
            }
        }
        return result;
    }

    /**
     * @return void
     * @description 异步定时多次删除文件
     * @author 刘鑫（1661）
     * @Params [filePath, Times, millies]
     * @date 2019/9/20 15:53
     */
    public void executorDeleteFile(String filePath, int Times, long millies) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            String finalPath = filePath;
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        for (int i = 0; i < Times; i++) {
                            Thread.sleep(millies);
                            if (deleteFile(finalPath)) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("删除文件线程出错！");
                    }
                }
            });
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除文件失败！！");
        }
    }

    /**
     * @return boolean
     * @description 删除文件
     * @author 刘鑫（1661）
     * @Params [filename, filepath]
     * @date 2019/9/20 13:53
     */
    public boolean deleteFile(String filepath) {
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            file.delete();
            return true;
        }
    }

    /**
     * @return com.itextpdf.text.pdf.BaseFont
     * @description 获取字体
     * @author 刘鑫（1661）
     * @Params [fontName]
     * @date 2019/10/31 21:12
     */
    public static BaseFont getLocalFont(String fontName) throws IOException, DocumentException {
        BaseFont result = null;
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("font/" + fontName);
        if (stream == null) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("font/Alibaba-PuHuiTi-Regular.otf");
        }
        assert stream != null;
        byte[] st1 = new byte[stream.available()];
        stream.read(st1);
        result = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, BaseFont.NOT_CACHED,
                st1, st1);
        return result;

    }
}
