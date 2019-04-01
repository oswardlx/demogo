//package com.example.util.test;
//
//import com.example.entity.Man;
//import com.example.service.ManService;
//import com.lowagie.text.*;
//import com.lowagie.text.Font;
//import com.lowagie.text.pdf.PdfDocument;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//
//import java.awt.*;
//import java.io.OutputStream;
//import java.util.List;
//
//public class PdfDemo {
//    public static void main(String [] args) throws DocumentException {
//        Document document = pdfgogo();
//        OutputStream outputStream = null;
//        PdfWriter.getInstance(document,outputStream);
//    }
//    public static Document pdfgogo(){
//        try{
//            Document document = new Document();
//            // A4纸张
//            document.setPageSize(PageSize.A4);
//            //标题
//            document.addTitle("用户信息");
//            //换行
//            document.add(new Chunk("\n"));
//            //表格，3列
//            PdfPTable table  = new PdfPTable(3);
//            //单元格
//            PdfPCell cell = null;
//            //字体，定义为蓝色加粗
//            Font f8 = new Font();
//            f8.setColor(Color.blue);
//            f8.setStyle(Font.BOLD);
//            //标题
//            cell = new PdfPCell(new Paragraph("id",f8));
//            //居中对其
//            cell.setHorizontalAlignment(1);
//            //将单元格加入表格
//            table.addCell(cell);
//
//
//            //标题
//            cell = new PdfPCell(new Paragraph("name",f8));
//            //居中对齐
//            cell.setHorizontalAlignment(1);
//            //将单元格加入表格
//            table.addCell(cell);
//
//
//            //获取数据模型中的manList
//            List<Man> manList = new ManService().getManList();
//            for(Man man : manList){
//                document.add(new Chunk("\n"));
//                cell = new PdfPCell(new Paragraph(man.getId()+""));
//                //居中对齐
//                cell.setHorizontalAlignment(1);
//                table.addCell(cell);
//                cell = new PdfPCell(new Paragraph(man.getAge()+""));
//                //居中对齐
//                cell.setHorizontalAlignment(1);
//                table.addCell(cell);
//                cell = new PdfPCell(new Paragraph(man.getName()));
//                //居中对齐
//                cell.setHorizontalAlignment(1);
//                table.addCell(cell);
//            }
//            document.add(table);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
