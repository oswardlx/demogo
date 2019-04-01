package com.example.util.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class PrePdf {
    public static  void main(String[] args) throws Exception {
//        createPDF();
    }
    public  String createPDF(OutputStream outputStream) throws Exception{
        String outPath = "/Users/oswardliu/IdeaProjects/demo/src/main/resources/templates/test"+System.currentTimeMillis()+".pdf";
        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);
        //设置横向
        rect = rect.rotate();

        //创建文档实例
        Document doc=new Document(rect);

        //添加中文字体
        BaseFont bfChinese=BaseFont.createFont();

        //设置字体样式
        Font textFont = new Font(bfChinese,11,Font.NORMAL); //正常

        //手指图片
        Image hand = Image.getInstance("/Users/oswardliu/IdeaProjects/demo/src/main/resources/templates/gogo.jpg");

        //创建输出流
        PdfWriter.getInstance(doc,outputStream );

        doc.open();
        doc.newPage();

        //块
        Chunk c1 = new Chunk("*sasdasdda*", textFont) ;

        doc.add(c1);

        doc.close();
        return outPath;
    }

}
