package com.example.entity;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.Serializable;

/**
 * @author liuxin
 * @Description: 行内容项参数
 * @date 2019/4/17 20:52
 */

public class PdfStuInfoModel implements Serializable {
    /**
     * 定位行
     */
    private int rowNum;
    /**
     * 定位列
     */
    private int colNum;
    /**
     * 项名
     */
    private String itemName;
    /**
     * 项信息
     */
    private String itemInfo;

    /**
     * 水平对齐项名   参照
     *
     * @see com.itextpdf.text.Element.ALIGN_LEFT
     */
    private int horizontalAlignmentName;

    /**
     * 垂直对齐项名
     *
     * @see PdfStuInfoModel.horizontalAlignmentName
     */
    private int verticalAlignmentName;

    /**
     * 水平对齐项内容   参照
     *
     * @see com.itextpdf.text.Element.ALIGN_LEFT
     */
    private int horizontalAlignmentInfo;

    /**
     * 垂直对齐项参数
     *
     * @see PdfStuInfoModel.horizontalAlignmentInfo
     */
    private int verticalAlignmentInfo;

    /**
     * 横向跨度
     */
    private int horizontalSpan;

    /**
     * 垂直跨度
     */
    private int verticalSpan;

    /**
     * 字体大小
     */
    private int fontSize;

    /**
     * 字体样式
     */
    private int fontStyle;

    /**
     * 字体
     */
    private Font font;

    /**
     * 边框
     */
    private int border;

    /**
     * 宽度占比
     */
    private double ratio;

    /**
     * 字体最大尺寸
     */
    private int maxSize;

    /**
     * 是否自适应
     */
    private boolean isAdaptive;

    /**
     * 高度
     */
    private int height;

    /**
     * 直接cell
     */
    private PdfPCell cell;

    /**
     * 字体最小尺寸
     */
    private int minSize;

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
    public PdfStuInfoModel(String info, int hAlign, int vAlign, int hSpan, int vSpan, double ratio, Font font, int maxSize, boolean isAdaptive) {
        this(info, hAlign, vAlign, hSpan, vSpan, ratio, font, isAdaptive);
        this.maxSize = maxSize < 1 ? 9 : maxSize;
    }

    public PdfStuInfoModel(String info, int hAlign, int vAlign, int hSpan, int vSpan, double ratio, Font font, int maxSize, int height, boolean isAdaptive) {
        this(info, hAlign, vAlign, hSpan, vSpan, ratio, font, isAdaptive);
        this.maxSize = maxSize < 1 ? 9 : maxSize;
        this.height = height;
    }

    public PdfPCell getCell() {
        return cell;
    }

    public void setCell(PdfPCell cell) {
        this.cell = cell;
    }

    public PdfStuInfoModel(PdfPCell cell) {
        this.cell = cell;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean isAdaptive() {
        return isAdaptive;
    }

    public void setAdaptive(boolean adaptive) {
        isAdaptive = adaptive;
    }

    public PdfStuInfoModel(String itemInfo, int horizontalAlignmentName, int verticalAlignmentName, int horizontalSpan, int verticalSpan, double ratio, int maxSize, boolean isAdaptive) {
        this.itemInfo = itemInfo;
        this.horizontalAlignmentName = horizontalAlignmentName;
        this.verticalAlignmentName = verticalAlignmentName;
        this.horizontalSpan = horizontalSpan;
        this.verticalSpan = verticalSpan;
        this.ratio = ratio;
        this.maxSize = maxSize < 1 ? 9 : maxSize;
        this.isAdaptive = isAdaptive;
    }

    public PdfStuInfoModel(String itemInfo, int horizontalAlignmentName, int verticalAlignmentName, int horizontalSpan, int verticalSpan, double ratio, Font font, boolean isAdaptive) {
        this.itemInfo = itemInfo;
        this.horizontalAlignmentName = horizontalAlignmentName;
        this.verticalAlignmentName = verticalAlignmentName;
        this.horizontalSpan = horizontalSpan;
        this.verticalSpan = verticalSpan;
        this.ratio = ratio;
        this.font = font;
        this.isAdaptive = isAdaptive;
        this.maxSize = maxSize < 1 ? 9 : maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public PdfStuInfoModel() {
        this.verticalSpan = 1;
        this.horizontalSpan = 1;
    }

    public PdfStuInfoModel(int rowNum, int colNum, String itemName, String itemInfo) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.itemName = itemName;
        this.itemInfo = itemInfo;
    }

    public int getHorizontalAlignmentName() {
        return horizontalAlignmentName;
    }

    public void setHorizontalAlignmentName(int horizontalAlignmentName) {
        this.horizontalAlignmentName = horizontalAlignmentName;
    }

    public int getVerticalAlignmentName() {
        return verticalAlignmentName;
    }

    public void setVerticalAlignmentName(int verticalAlignmentName) {
        this.verticalAlignmentName = verticalAlignmentName;
    }

    public int getHorizontalAlignmentInfo() {
        return horizontalAlignmentInfo;
    }

    public void setHorizontalAlignmentInfo(int horizontalAlignmentInfo) {
        this.horizontalAlignmentInfo = horizontalAlignmentInfo;
    }

    public int getVerticalAlignmentInfo() {
        return verticalAlignmentInfo;
    }

    public void setVerticalAlignmentInfo(int verticalAlignmentInfo) {
        this.verticalAlignmentInfo = verticalAlignmentInfo;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public int getHorizontalSpan() {
        return horizontalSpan;
    }

    public void setHorizontalSpan(int horizontalSpan) {
        this.horizontalSpan = horizontalSpan;
    }

    public int getVerticalSpan() {
        return verticalSpan;
    }

    public void setVerticalSpan(int verticalSpan) {
        this.verticalSpan = verticalSpan;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }
}
