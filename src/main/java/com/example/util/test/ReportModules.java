package com.example.util.test;

import com.example.entity.PdfStuInfoModel;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 报表组件类
 * @author      刘鑫（1661）
 * @date        2019/10/25 9:33
 */
@Service
public class ReportModules {
    @Resource
    JwglxtXscjzbUtil jUtil = new JwglxtXscjzbUtil();

    /**
     * @description 封装标题组件
     * @author      刘鑫（1661）
     * @return      com.itextpdf.text.pdf.PdfPTable
     * @Params      [xxmc, maxTitleSize, titleHeight, headerFont, marginLeft, marginRight, rectangle, xscjzbdyModelOne, headArr, hSpan, vSpan]
     * @date        2019/10/25 9:44
     */
    public PdfPTable getTitleModuleToTable(String titleName, String schoolName, String tailStr,  int titleHeight, Font headerFont, float marginLeftRight, Rectangle rectangle) throws IOException, DocumentException {
        int arrSumTemp;
        int[] headArr = new int[]{1};
        List<PdfStuInfoModel> rowModelList = new ArrayList<PdfStuInfoModel>();
        PdfPTable tempTable;
        arrSumTemp = jUtil.getIntegerArrSum(headArr);
        //标题名称缺省值为：学校名称+尾部字符串的形式
        titleName = (titleName==null || titleName.length()==0) ? schoolName + tailStr : titleName;
        rowModelList.add(jUtil.getPdfStuInfoModel(titleName, (double) arrSumTemp / arrSumTemp, headerFont, 15, titleHeight, 1, 1, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, false));
        tempTable = jUtil.getTableAdaptivePro(headArr, marginLeftRight, PdfPCell.NO_BORDER, rectangle, rowModelList, headerFont.getBaseFont());
        return tempTable;
    }

    /**
     * @description add a line
     * @author      刘鑫（1661）
     * @return      com.itextpdf.text.pdf.PdfPTable
     * @Params      [xxmc, maxTitleSize, titleHeight, headerFont, marginLeft, marginRight, rectangle, xscjzbdyModelOne, headArr, hSpan, vSpan]
     * @date        2019/10/25 9:44
     */
    public PdfPTable getOneLine(String titleName, int hAlign, int vAlign, int border, int titleHeight, Font headerFont, float marginLeftRight, Rectangle rectangle) throws IOException, DocumentException {
        int arrSumTemp;
        int[] headArr = new int[]{1};
        List<PdfStuInfoModel> rowModelList = new ArrayList<PdfStuInfoModel>();
        PdfPTable tempTable;
        arrSumTemp = jUtil.getIntegerArrSum(headArr);
        //标题名称缺省值为：学校名称+尾部字符串的形式
        rowModelList.add(jUtil.getPdfStuInfoModel(titleName, (double) arrSumTemp / arrSumTemp, headerFont, 15, titleHeight, 1, 1, hAlign, vAlign, false));
        tempTable = jUtil.getTableAdaptivePro(headArr, marginLeftRight, border, rectangle, rowModelList, headerFont.getBaseFont());
        return tempTable;
    }


}
